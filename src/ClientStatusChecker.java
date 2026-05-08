@FunctionalInterface
public interface ClientStatusChecker {
    boolean checkStatus(ClientStatus status);
}