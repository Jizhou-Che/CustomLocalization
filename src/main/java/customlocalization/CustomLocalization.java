package customlocalization;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModLabeledButton;
import basemod.ModPanel;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.ConfigUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.localization.*;
import customlocalization.util.TextureLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SpireInitializer
public class CustomLocalization implements PostInitializeSubscriber, EditStringsSubscriber {
    public static final Logger logger = LogManager.getLogger(CustomLocalization.class.getName());
    private static final String configDirectory = ConfigUtils.CONFIG_DIR + File.separator + "CustomLocalization" + File.separator;

    public CustomLocalization() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new CustomLocalization();
    }

    public void receivePostInitialize() {
        initializeConfigPanel();
    }

    public void receiveEditStrings() {
        initializeBackupLocalizationFiles();
        loadLocalizationFiles(Settings.language);
    }

    private void initializeConfigPanel() {
        logger.info("CustomLocalization | Initializing config panel...");
        ModPanel configPanel = new ModPanel();
        BaseMod.registerModBadge(TextureLoader.getTexture("customlocalization/images/ui/Badge.png"), "CustomLocalization", "瓶装易碎柯妮法", "A custom localization mod for Dai.", configPanel);
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("customlocalization:Config");
        ModLabeledButton labeledButton1 = new ModLabeledButton(uiStrings.TEXT[0], 400.0F, 650.0F, configPanel,
                (me) -> {
                    try {
                        Desktop.getDesktop().open(new File(configDirectory));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        ModLabel label1 = new ModLabel(uiStrings.TEXT[1], 400.0F, 250.0F, Settings.RED_TEXT_COLOR, configPanel,
                (me) -> {
                    // Update function.
                });
        configPanel.addUIElement(labeledButton1);
        configPanel.addUIElement(label1);
    }

    private void initializeBackupLocalizationFiles() {
        try {
            File configDir = new File(configDirectory);
            boolean configDirJustCreated = configDir.mkdirs();

            // Copy the README file.
            InputStream configREADME = CustomLocalization.class.getResourceAsStream("/customlocalization/config/README.txt");
            assert configREADME != null;
            Files.copy(configREADME, Paths.get(configDirectory + "README.txt"), StandardCopyOption.REPLACE_EXISTING);

            // Recreate the backup directory.
            String configBackupDirectory = configDirectory + "backup" + File.separator;
            deleteDirectory(new File(configBackupDirectory));
            InputStream backupIS = CustomLocalization.class.getResourceAsStream("/customlocalization/config/backup.zip");
            assert backupIS != null;
            ZipInputStream backupZIS = new ZipInputStream(backupIS);
            ZipEntry backupZE = backupZIS.getNextEntry();
            while (backupZE != null) {
                Path targetPath = Paths.get(configDirectory).resolve(backupZE.getName()).normalize();
                if (backupZE.isDirectory()) {
                    Files.createDirectories(targetPath);
                } else {
                    if (targetPath.getParent() != null && Files.notExists(targetPath.getParent())) {
                        Files.createDirectories(targetPath.getParent());
                    }
                    Files.copy(backupZIS, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
                backupZE = backupZIS.getNextEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLocalizationFiles(Settings.GameLanguage language) {
        if (language == GameLanguage.ZHS) {
            BaseMod.loadCustomStringsFile(UIStrings.class, "customlocalization/localization/zhs/ui.json");
        } else {
            BaseMod.loadCustomStringsFile(UIStrings.class, "customlocalization/localization/eng/ui.json");
        }

        if ((new File(configDirectory + "achievements.json")).isFile()) {
            BaseMod.loadCustomStringsFile(AchievementStrings.class, configDirectory + "achievements.json");
        }
        if ((new File(configDirectory + "blights.json")).isFile()) {
            BaseMod.loadCustomStringsFile(BlightStrings.class, configDirectory + "blights.json");
        }
        if ((new File(configDirectory + "cards.json")).isFile()) {
            BaseMod.loadCustomStringsFile(CardStrings.class, configDirectory + "cards.json");
        }
        if ((new File(configDirectory + "characters.json")).isFile()) {
            BaseMod.loadCustomStringsFile(CharacterStrings.class, configDirectory + "characters.json");
        }
        if ((new File(configDirectory + "credits.json")).isFile()) {
            BaseMod.loadCustomStringsFile(CreditStrings.class, configDirectory + "credits.json");
        }
        if ((new File(configDirectory + "events.json")).isFile()) {
            BaseMod.loadCustomStringsFile(EventStrings.class, configDirectory + "events.json");
        }
        if ((new File(configDirectory + "keywords.json")).isFile()) {
            BaseMod.loadCustomStringsFile(KeywordStrings.class, configDirectory + "keywords.json");
        }
        if ((new File(configDirectory + "monsters.json")).isFile()) {
            BaseMod.loadCustomStringsFile(MonsterStrings.class, configDirectory + "monsters.json");
        }
        if ((new File(configDirectory + "orbs.json")).isFile()) {
            BaseMod.loadCustomStringsFile(OrbStrings.class, configDirectory + "orbs.json");
        }
        if ((new File(configDirectory + "potions.json")).isFile()) {
            BaseMod.loadCustomStringsFile(PotionStrings.class, configDirectory + "potions.json");
        }
        if ((new File(configDirectory + "powers.json")).isFile()) {
            BaseMod.loadCustomStringsFile(PowerStrings.class, configDirectory + "powers.json");
        }
        if ((new File(configDirectory + "relics.json")).isFile()) {
            BaseMod.loadCustomStringsFile(RelicStrings.class, configDirectory + "relics.json");
        }
        if ((new File(configDirectory + "run_mods.json")).isFile()) {
            BaseMod.loadCustomStringsFile(RunModStrings.class, configDirectory + "run_mods.json");
        }
        if ((new File(configDirectory + "score_bonuses.json")).isFile()) {
            BaseMod.loadCustomStringsFile(ScoreBonusStrings.class, configDirectory + "score_bonuses.json");
        }
        if ((new File(configDirectory + "stances.json")).isFile()) {
            BaseMod.loadCustomStringsFile(StanceStrings.class, configDirectory + "stances.json");
        }
        if ((new File(configDirectory + "tutorials.json")).isFile()) {
            BaseMod.loadCustomStringsFile(TutorialStrings.class, configDirectory + "tutorials.json");
        }
        if ((new File(configDirectory + "ui.json")).isFile()) {
            BaseMod.loadCustomStringsFile(UIStrings.class, configDirectory + "ui.json");
        }
    }

    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    boolean successfullyDeleted = f.delete();
                }
            }
        }
        boolean successfullyDeleted = directory.delete();
    }
}
