package workflow.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class AwsService {
    private final String accessKey;
    private final String secretKey;

    public AwsService (String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public void AddToSqs(String message) {
        try {
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                    .withRegion(Regions.US_EAST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();

            SendMessageRequest request = new SendMessageRequest()
                    .withQueueUrl("https://sqs.us-east-2.amazonaws.com/287642671827/OutgoingFleetMessages")
                    .withMessageBody(message);
            sqs.sendMessage(request);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
