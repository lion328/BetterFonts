package me.isuzutsuki.betterfonts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BTFClassTransformer implements net.minecraft.launchwrapper.IClassTransformer{

    @Override
    public byte[] transform(String obfName, String fullName, byte[] contents) {
        if (fullName.equals("net.minecraft.client.gui.FontRenderer")) {
            BetterFontsCore.BETTER_FONTS_LOGGER.info("[BetterFonts] Transformer is about to patch : " + obfName);
            contents = patchClassInJar(fullName, contents, obfName, BetterFontsCore.location);
        }
        return contents;

    }

    public byte[] patchClassInJar(String name, byte[] bytes, String ObfName, File location){
        try {
            ZipFile zip = new ZipFile(location);
            ZipEntry entry = zip.getEntry(name.replace('.', '/') + ".class");

            if (entry == null) {
                BetterFontsCore.BETTER_FONTS_LOGGER.error(name + " not found in " + location.getName());
            } else {
                InputStream zin = zip.getInputStream(entry);
                int size = (int) entry.getSize();
                byte[] newBytes = new byte[size];
                int pos = 0;
                while (pos < size) {
                    int len = zin.read(newBytes,pos,size-pos);
                    if (len == 0)
                        throw new IOException();
                    pos += len;
                }
                if(!newBytes.equals(bytes))bytes=newBytes;
                zin.close();
                BetterFontsCore.BETTER_FONTS_LOGGER.info("Patched class " + name + " with BetterFont's !");
            }
            zip.close();
        } catch (Exception e) {
            throw new RuntimeException("Error overriding " + name + " from " + location.getName(), e);
        }
        return bytes;
    }
}