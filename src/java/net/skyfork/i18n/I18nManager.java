package net.skyfork.i18n;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import lombok.SneakyThrows;
import net.minecraft.util.ResourceLocation;
import net.skyfork.Client;
import net.skyfork.Wrapper;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author LangYa
 * @since 2024/6/6 下午7:51
 */

public class I18nManager implements Wrapper {

    private static final Splitter splitter = Splitter.on('=').limit(2);
    private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d.]*[df]");

    public I18nManager() {
        I18n_Client.properties = new HashMap<>();
        loadProperties(LanguageType.English);
    }

    @SneakyThrows
    public void loadProperties(LanguageType languageType) {
        System.out.println(languageType.name);
        ResourceLocation location = Client.getLocation("lang/" + languageType.resource + ".lang");
        InputStream inputStream = mc.getResourceManager().getResource(location).getInputStream();
        for (String line : IOUtils.readLines(inputStream, StandardCharsets.UTF_8)) {
            if (!line.isEmpty() && line.charAt(0) != 35)
            {
                String[] astring = Iterables.toArray(splitter.split(line), String.class);

                if (astring != null && astring.length == 2)
                {
                    String key = astring[0];
                    String value = pattern.matcher(astring[1]).replaceAll("%$1s");
                    I18n_Client.properties.put(key, value);
                }
            }
        }
        Client.displayName = I18n_Client.format("client.name");
    }

}
