package org.example;

import java.util.concurrent.StructuredTaskScope;

public class JEP506 {

    private static final ScopedValue<RequestContext> REQUEST_CONTEXT =
            ScopedValue.newInstance();

    private static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

    static void main() {
        IO.println("=== Scoped Value Demo ===\n");

        handleRequest("req-01", "John");
        handleRequest("req-02", "Alice");
        handleRequest("req-03", "Ali");


    }

    static void handleRequest(String requestId, String userId) {
        IO.println("🌐 Handling request: " + requestId);

        var context = new RequestContext(requestId, System.currentTimeMillis());

        ScopedValue.where(REQUEST_CONTEXT, context)
                .where(USER_ID, userId)
                .run(() -> {
                processUserRequest();
                });

        IO.println("✅ Request completed \n");
    }

    static void processUserRequest() {
        IO.println("  📝 Processing request for user: " + USER_ID.get());

        validateUser();
        fetchDataFromDatabase();
        logActivity();
    }

    static void validateUser() {
        String user = USER_ID.get();
        System.out.println("  🔐 Validating user: " + user);
        // Access to context without it being passed as parameter
    }

    static void fetchDataFromDatabase() {
        RequestContext ctx = REQUEST_CONTEXT.get();
        System.out.println("  💾 DB Query [Tx: " + ctx.requestId() + "]");
        // Context available deep in the call stack
    }


    static void logActivity() {
        RequestContext context = REQUEST_CONTEXT.get();
        String user = USER_ID.get();
        System.out.println("  📊 Log: User=" + user + ", RequestId=" + context.requestId());
    }

    static void handleConcurrentRequest(String requestId, String userId) {
        IO.println("🔀 Handling concurrent request: " + requestId);

        var context = new RequestContext(requestId, System.currentTimeMillis());

        ScopedValue.where(REQUEST_CONTEXT, context)
                .where(USER_ID, userId)
                .run(() -> {
                    // Use the full nested name here
                    try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())) {

                        // Example: Forking a task
                        // scope.fork(() -> someMethod());

                        scope.join();           // Wait for all threads

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (Throwable e) {
                        throw new RuntimeException("Subtask failed!", e);
                    }
                });
    }

}

record RequestContext(String requestId, long timestamp) {
}
