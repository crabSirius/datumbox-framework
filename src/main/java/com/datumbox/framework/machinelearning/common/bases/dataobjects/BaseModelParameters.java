/**
 * Copyright (C) 2013-2015 Vasilis Vryniotis <bbriniotis at datumbox.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.datumbox.framework.machinelearning.common.bases.dataobjects;

import com.datumbox.common.objecttypes.Learnable;
import com.datumbox.common.persistentstorage.interfaces.DatabaseConnector;
import com.datumbox.common.persistentstorage.interfaces.BigMap;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Base class for every ModelParameter class in the framework. It automatically
 * initializes all the BidMap fields by using reflection.
 * 
 * @author bbriniotis
 */
public abstract class BaseModelParameters implements Learnable {
    
    public BaseModelParameters(DatabaseConnector dbc) {
        //Initialize all the BigMap fields
        bigMapInitializer(dbc);
    }
    
    public final void bigMapInitializer(DatabaseConnector dbc) {
        //get all the fields from all the inherited classes
        for(Field field : getAllFields(new LinkedList<>(), this.getClass())){
            
            //if the field is annotated with BigMap
            if (field.isAnnotationPresent(BigMap.class)) {
                field.setAccessible(true);
                
                try {
                    //call the getBigMap method to load it
                    field.set(this, dbc.getBigMap(field.getName(), false));
                } 
                catch (IllegalArgumentException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                
            }
        }
    }
    
    private List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}