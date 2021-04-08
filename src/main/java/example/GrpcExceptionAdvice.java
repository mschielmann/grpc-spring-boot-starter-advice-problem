package example;

import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.beans.factory.annotation.Value;

@GrpcAdvice
public class GrpcExceptionAdvice {
    private final boolean exampleParam;

    public GrpcExceptionAdvice(@Value("${example.param:false}") boolean exampleParam) {
        // GrpcAdviceIsPresentCondition creates this bean as a side effect of:
        // !safeBeanFactory.getBeansWithAnnotation(GrpcAdvice.class)
        // and it is there required for this bean to have a default constructor
        // if `getBeanNamesForAnnotation` has been used instead,
        // this problem would not occur.
        System.out.println("Creating advice with param value: " + exampleParam);
        this.exampleParam = exampleParam;
    }

    @GrpcExceptionHandler(RuntimeException.class)
    public StatusException handleApplicationException(RuntimeException e) {
        System.out.println("Logging error " + e.getMessage());
        return new StatusException(Status.INTERNAL);
    }
}
