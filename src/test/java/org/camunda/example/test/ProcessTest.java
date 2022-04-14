package org.camunda.example.test;

import com.camunda.example.Application;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ProcessTest extends AbstractProcessEngineRuleTest {

  @Value("${spring.datasource.url}")
  String urlProp;

  @Test
  public void testHappyPath() {

    ProcessInstance pi = runtimeService().startProcessInstanceByKey("example-process", withVariables("myVar", "myVarValue"));
    assertThat(pi).isEnded().variables().containsEntry("groovyOutput", urlProp);
  }

}
