package com.k458.sem12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.Arrays;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel textInputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChannel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> mainTransformer(){
        return text -> {
            String[] split = text.split(" ");
            if (split[2].equals("bastard")) split[2] = "samsung";
            else split[2] = "apple";
            return Arrays.toString(split);
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler messageHandler(){
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File(
                        "./files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
