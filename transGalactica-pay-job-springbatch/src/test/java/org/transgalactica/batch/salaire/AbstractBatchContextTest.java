package org.transgalactica.batch.salaire;

import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class })
public abstract class AbstractBatchContextTest extends AbstractContextTest {

}
