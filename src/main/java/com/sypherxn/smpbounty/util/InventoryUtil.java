package com.sypherxn.smpbounty.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class InventoryUtil {

    //Converts ItemStack list from an inventory into a string
    public static String inventoryToString(List<ItemStack> items) {

        if(items.size() == 0) return "";
        else {

            try {

                ByteArrayOutputStream io = new ByteArrayOutputStream();
                BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

                os.writeInt(items.size());

                for(ItemStack item: items) {
                    os.writeObject(item);
                }

                os.flush();

                byte[] rawData = io.toByteArray();

                String encodedData = Base64.getEncoder().encodeToString(rawData);

                os.close();

                return encodedData;

            } catch (IOException ex)  { System.out.println(ex); }

        }

        return "";

    }

    public static ArrayList<ItemStack> stringToInventory(String encodedData) {

        ArrayList<ItemStack> items = new ArrayList<>();

        if(!encodedData.isEmpty()) {

            byte [] rawData = Base64.getDecoder().decode(encodedData);

            try {

                ByteArrayInputStream io = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream is = new BukkitObjectInputStream(io);

                int itemsSize = is.readInt();

                for(int i = 0; i < itemsSize; i++) {

                    items.add((ItemStack) is.readObject());

                }

                is.close();

            } catch (IOException | ClassNotFoundException ex) { System.out.println(ex); }

        }

        return items;

    }

}
