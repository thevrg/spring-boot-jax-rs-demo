package hu.dpc.edu;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class MyJava8LoggerTest {
    @Test
    public void logWhenLevelIsLoggable() throws Exception {
        //Given
        Logger logger = mock(Logger.class);
        when(logger.isLoggable(Level.FINER)).thenReturn(true);
        final MyJava8Logger sut = new MyJava8Logger(logger);

        //When
        sut.log(Level.FINER, () -> "finer message");

        //Then
        verify(logger).log(Level.FINER, "finer message");


    }
    @Test
    public void doNotLogWhenLevelIsNotLoggable() throws Exception {
        //Given
        Logger logger = mock(Logger.class);
        when(logger.isLoggable(Level.FINER)).thenReturn(false);
        final MyJava8Logger sut = new MyJava8Logger(logger);
        final MessageProducer producer = mock(MessageProducer.class);
        //When
        sut.log(Level.FINER, producer);

        //Then
        verify(producer, Mockito.never()).produceMessage();
        verify(logger, Mockito.never()).log(any(Level.class), anyString());

    }

}