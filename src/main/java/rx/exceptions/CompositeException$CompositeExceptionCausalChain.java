package rx.exceptions;

final class CompositeException$CompositeExceptionCausalChain extends RuntimeException {
    CompositeException$CompositeExceptionCausalChain() {
    }

    public String getMessage() {
        return "Chain of Causes for CompositeException In Order Received =>";
    }
}
