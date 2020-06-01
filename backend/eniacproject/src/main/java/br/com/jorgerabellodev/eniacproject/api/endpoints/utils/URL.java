package br.com.jorgerabellodev.eniacproject.api.endpoints.utils;

import static java.util.Arrays.stream;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Long> decodeLongList(String argument) {
        return stream(argument.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static String decodeParam(String argument) {
        try {
            return URLDecoder.decode(argument, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
