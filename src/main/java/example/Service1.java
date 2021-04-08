package example;

import example.proto.ExampleMethodResponse;
import example.proto.ExampleService1Grpc;
import example.proto.ExampleService2Grpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class Service1 extends ExampleService1Grpc.ExampleService1ImplBase {

    @GrpcClient("service2")
    private ExampleService2Grpc.ExampleService2BlockingStub service2;

    @Override
    public void exampleMethod(example.proto.ExampleMethodRequest request,
                              StreamObserver<ExampleMethodResponse> responseObserver) {
        System.out.println("#########");
        System.out.println("Service1 - after initial call: ");

        service2.exampleMethod(request);
        System.out.println("Service1 - after service2 call: ");

        responseObserver.onNext(ExampleMethodResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
