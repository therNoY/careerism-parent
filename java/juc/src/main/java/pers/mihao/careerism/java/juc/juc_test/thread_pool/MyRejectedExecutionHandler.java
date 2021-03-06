package pers.mihao.careerism.java.juc.juc_test.thread_pool;


public interface MyRejectedExecutionHandler {

    /**
     * Method that may be invoked by a {@link MyThreadPoolExecutor} when
     * {@link MyThreadPoolExecutor#execute execute} cannot accept a
     * task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon
     * shutdown of the Executor.
     *
     * <p>In the absence of other alternatives, the method may throw
     * an unchecked {@link MyRejectedExecutionHandler}, which will be
     * propagated to the caller of {@code execute}.
     *
     * @param r the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     * @throws MyThreadPoolExecutor if there is no remedy
     */
    void rejectedExecution(Runnable r, MyThreadPoolExecutor executor);
}
