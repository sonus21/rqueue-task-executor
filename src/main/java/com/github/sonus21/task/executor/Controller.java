package com.github.sonus21.task.executor;

import com.github.sonus21.rqueue.producer.RqueueMessageSender;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class Controller {
  private @NonNull RqueueMessageSender rqueueMessageSender;

  @Value("${email.queue.name}")
  private String emailQueueName;

  @Value("${invoice.queue.name}")
  private String invoiceQueueName;

  @Value("${invoice.queue.delay}")
  private Long invoiceDelay;

  @GetMapping("email")
  public String sendEmail(
      @RequestParam String email, @RequestParam String subject, @RequestParam String content) {
    log.info("Sending email");
    rqueueMessageSender.put(emailQueueName, new Email(email, subject, content));
    return "Please check your inbox!";
  }

  @GetMapping("invoice")
  public String generateInvoice(@RequestParam String id, @RequestParam String type) {
    log.info("Generate invoice");
    rqueueMessageSender.put(invoiceQueueName, new Invoice(id, type), invoiceDelay);
    return "Invoice would be generated in " + invoiceDelay + " milliseconds";
  }
}
