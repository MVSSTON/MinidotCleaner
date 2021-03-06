package me.mstn.desktop;

import me.mstn.desktop.command.AbstractCommand;
import me.mstn.desktop.command.impl.ListCommand;
import me.mstn.desktop.command.impl.StartCommand;
import me.mstn.desktop.configuration.CleanerConfiguration;
import me.mstn.desktop.util.ModelUtility;
import me.mstn.desktop.util.logger.LoggerHandler;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class Start {

    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().reset();
        MinidotCleaner.getLogger().addHandler(new LoggerHandler());

        MinidotCleaner.getLogger().info("---- STARTING MINIDOTCLEANER 1.3b ----");
        MinidotCleaner.getLogger().info("# Find a bug? Report! https://github.com/MVSSTON/MinidotCleaner/issues");
        MinidotCleaner.getLogger().info("# Contact with author - https://t.me/masston");
        MinidotCleaner.getLogger().info("--------------------------------------");
        MinidotCleaner.getLogger().info("");

        Path path = Paths.get("./config.yml");
        Constructor configurationModel = new Constructor(CleanerConfiguration.class);
        Yaml yaml = new Yaml(configurationModel);

        if (!path.toFile().exists()) {
            InputStream inputStream = Start.class.getResourceAsStream("/config.yml");
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            MinidotCleaner.stop(Level.INFO, "Configuration file has been created, close the program and configure it or start the program again.");
        }

        CleanerConfiguration configuration = yaml.load(new FileInputStream(path.toFile()));
        MinidotCleaner.setConfiguration(configuration);

        String userName = System.getProperty("user.name");
        String vimeWorldPath = configuration.getPath()
                .replace("@user", userName);
        MinidotCleaner.setVimeWorldPath(vimeWorldPath);

        MinidotCleaner.getModelsList().addAll(
                ModelUtility.getModelsList(
                        vimeWorldPath + configuration.getDirection().getMinidotPath()
                )
        );

        if (args.length > 0) {
            if (args.length == 1) {
                String commandArg = args[0];

                if (commandArg.equalsIgnoreCase("--list")) {
                    AbstractCommand command = new ListCommand();
                    command.execute(args);
                }

                return;
            }

            return;
        }

        AbstractCommand command = new StartCommand();
        command.execute(args);
    }

}
