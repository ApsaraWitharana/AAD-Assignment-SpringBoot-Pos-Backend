package lk.ijse.gdse68.springpossystem.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author : sachini
 * @date : 2024-10-04
 **/
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
}
