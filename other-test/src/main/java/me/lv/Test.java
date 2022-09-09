package me.lv;

import org.modelmapper.ModelMapper;

/**
 * @author plume
 * @date 2021/8/19 14:22
 */
public class Test {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

    }

}
