package net.skyfork.user;

import com.cubk.event.annotations.EventTarget;
import net.minecraft.util.EnumChatFormatting;
import net.skyfork.Client;
import net.skyfork.event.impl.misc.EventText;

/**
 * @author LangYa
 * @since 2024/6/5 下午9:25
 */

public class RankManager {
    public static final String PRIMARY_COLOR = EnumChatFormatting.RED.toString();
    public static final String SECONDARY_COLOR = EnumChatFormatting.GRAY.toString();

    public RankManager() {
        Client.eventManager.register(this);
    }

    @EventTarget
    public void onT(EventText e) {
        // 2582457270 赞助用户 20人民币
        set(e,"NotChisken","SponsorShip");
        set(e,"lindsey614","SponsorShip");

        // 2696478875 生病龙虾 SickLobster
        set(e,"UnfairLobster","Staff");

        // 3109983896 high ping hyp group admin
        set(e,"f_lyx","Frisk++");
        set(e,"lyx_frisk","Frisk++");

        // langya
        set(e,"Lang7a","Admin");
        set(e,"LangYa466","Admin");
    }

    private String getRank(String str, String color) {
        return SECONDARY_COLOR + "[" + color + str + SECONDARY_COLOR + "] ";
    }

    private void set(EventText e,String playerName,String rank) {
        boolean set = false;
        if (e.text.contains(playerName) && !set) {
            if (rank.equals("Admin")) {
                e.text = String.format("%s ", getRank(rank,PRIMARY_COLOR)) + e.text;
            }  else {
                e.text = String.format("%s ", getRank(rank,EnumChatFormatting.BLUE.toString())) + e.text;
            }
            set = true;
        }
    }

}
