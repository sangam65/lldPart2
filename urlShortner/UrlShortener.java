package urlShortner;

import java.util.*;

// Enums
enum UrlStatus {
    ACTIVE,
    EXPIRED,
    DELETED
}

// Model Classes
class UrlMapping {
    private String shortCode;
    private String longUrl;
    private long createdAt;
    private long expiresAt;  // -1 means no expiration
    private int clickCount;
    private UrlStatus status;

    public UrlMapping(String shortCode, String longUrl, long expiresAt) {
        this.shortCode = shortCode;
        this.longUrl = longUrl;
        this.createdAt = System.currentTimeMillis();
        this.expiresAt = expiresAt;
        this.clickCount = 0;
        this.status = UrlStatus.ACTIVE;
    }

    // Getters and Setters
    public String getShortCode() {
        return shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public int getClickCount() {
        return clickCount;
    }

    public UrlStatus getStatus() {
        return status;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }

    public void setStatus(UrlStatus status) {
        this.status = status;
    }

    public boolean isExpired() {
        if (expiresAt == -1) return false;  // No expiration
        return System.currentTimeMillis() > expiresAt;
    }
}

// Main Service Class
class UrlShortenerService {
    private Map<String, UrlMapping> shortToUrl;  // shortCode -> UrlMapping
    private Map<String, String> urlToShort;      // longUrl -> shortCode (for deduplication)
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private long counter;

    public UrlShortenerService() {
        this.shortToUrl = new HashMap<>();
        this.urlToShort = new HashMap<>();
        this.counter = 1;
    }

    // Shorten URL
    public String shortenUrl(String longUrl) {
        return shortenUrl(longUrl, -1);  // No expiration by default
    }

    public String shortenUrl(String longUrl, long expirationTimeMs) {
        if (longUrl == null || longUrl.isEmpty()) {
            throw new IllegalArgumentException("Long URL cannot be empty");
        }

        // If URL already shortened, return existing short code
        if (urlToShort.containsKey(longUrl)) {
            String existingCode = urlToShort.get(longUrl);
            UrlMapping mapping = shortToUrl.get(existingCode);
            if (!mapping.isExpired()) {
                return existingCode;
            }
        }

        // Generate unique short code
        String shortCode = generateShortCode();

        // Ensure uniqueness
        while (shortToUrl.containsKey(shortCode)) {
            shortCode = generateShortCode();
        }

        // Store mapping
        UrlMapping mapping = new UrlMapping(shortCode, longUrl, expirationTimeMs);
        shortToUrl.put(shortCode, mapping);
        urlToShort.put(longUrl, shortCode);

        return shortCode;
    }

    // Get original URL from short code
    public String getOriginalUrl(String shortCode) {
        if (shortCode == null || shortCode.isEmpty()) {
            throw new IllegalArgumentException("Short code cannot be empty");
        }

        if (!shortToUrl.containsKey(shortCode)) {
            return null;  // Short code not found
        }

        UrlMapping mapping = shortToUrl.get(shortCode);

        // Check if expired
        if (mapping.isExpired()) {
            mapping.setStatus(UrlStatus.EXPIRED);
            return null;
        }

        // Check if deleted
        if (mapping.getStatus() == UrlStatus.DELETED) {
            return null;
        }

        // Increment click count
        mapping.incrementClickCount();

        return mapping.getLongUrl();
    }

    // Delete short URL
    public void deleteUrl(String shortCode) {
        if (!shortToUrl.containsKey(shortCode)) {
            throw new IllegalArgumentException("Short code not found");
        }

        UrlMapping mapping = shortToUrl.get(shortCode);
        mapping.setStatus(UrlStatus.DELETED);
    }

    // Get URL statistics
    public int getClickCount(String shortCode) {
        if (!shortToUrl.containsKey(shortCode)) {
            return -1;
        }
        return shortToUrl.get(shortCode).getClickCount();
    }

    // Get all URLs (for testing/debugging)
    public List<UrlMapping> getAllMappings() {
        return new ArrayList<>(shortToUrl.values());
    }

    // Helper: Generate random short code using counter-based approach
    private String generateShortCode() {
        StringBuilder sb = new StringBuilder();
        long num = counter++;
        
        while (num > 0) {
            sb.append(CHARSET.charAt((int)(num % CHARSET.length())));
            num /= CHARSET.length();
        }

        // Pad to ensure minimum length
        while (sb.length() < SHORT_CODE_LENGTH) {
            sb.append('a');
        }

        return sb.reverse().toString();
    }
}

// Main class for testing
public class UrlShortener {
    public static void main(String[] args) {
        UrlShortenerService service = new UrlShortenerService();

        // Test 1: Shorten URL
        System.out.println("=== Test 1: Shorten URL ===");
        String longUrl1 = "https://www.example.com/very/long/url/that/needs/shortening";
        String shortCode1 = service.shortenUrl(longUrl1);
        System.out.println("Long URL: " + longUrl1);
        System.out.println("Short Code: " + shortCode1);

        // Test 2: Get original URL
        System.out.println("\n=== Test 2: Get Original URL ===");
        String retrieved = service.getOriginalUrl(shortCode1);
        System.out.println("Retrieved URL: " + retrieved);

        // Test 3: Click count
        System.out.println("\n=== Test 3: Click Count ===");
        service.getOriginalUrl(shortCode1);
        service.getOriginalUrl(shortCode1);
        System.out.println("Click count for " + shortCode1 + ": " + service.getClickCount(shortCode1));

        // Test 4: Multiple URLs
        System.out.println("\n=== Test 4: Multiple URLs ===");
        String longUrl2 = "https://www.google.com";
        String shortCode2 = service.shortenUrl(longUrl2);
        System.out.println("Short Code 2: " + shortCode2);

        // Test 5: Deduplication
        System.out.println("\n=== Test 5: Deduplication ===");
        String shortCode1Again = service.shortenUrl(longUrl1);
        System.out.println("Same long URL shortened again: " + shortCode1Again);
        System.out.println("Returns same code: " + shortCode1.equals(shortCode1Again));

        // Test 6: Delete URL
        System.out.println("\n=== Test 6: Delete URL ===");
        service.deleteUrl(shortCode2);
        String retrievedAfterDelete = service.getOriginalUrl(shortCode2);
        System.out.println("After deletion: " + retrievedAfterDelete);

        // Test 7: Expiration
        System.out.println("\n=== Test 7: Expiration ===");
        long expirationTime = System.currentTimeMillis() + 1000;  // 1 second
        String longUrl3 = "https://www.temporary.com";
        String shortCode3 = service.shortenUrl(longUrl3, expirationTime);
        System.out.println("Short code (expires in 1s): " + shortCode3);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String retrievedAfterExpiry = service.getOriginalUrl(shortCode3);
        System.out.println("After expiration: " + retrievedAfterExpiry);
    }
}