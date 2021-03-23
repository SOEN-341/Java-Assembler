public interface IErrorReporter {
    void record(ErrorMsg error);
    void report();
}
