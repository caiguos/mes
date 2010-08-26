package com.qcadoo.mes.core.data.internal;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.qcadoo.mes.core.data.beans.Entity;
import com.qcadoo.mes.core.data.definition.ColumnDefinition;
import com.qcadoo.mes.core.data.definition.FieldDefinition;

public class ExpressionUtilTest {

    @Test
    public void shouldReturnStringRepresentationOfOneFieldWithoutExpression() throws Exception {
        // given
        Entity entity = new Entity(1L);
        entity.setField("name", "Mr T");

        FieldDefinition fieldDefinition = new FieldDefinition("name");

        ColumnDefinition columnDefinition = new ColumnDefinition("col");
        columnDefinition.setFields(Lists.newArrayList(fieldDefinition));

        // when
        String value = ExpressionUtil.getValue(entity, columnDefinition);

        // then
        assertEquals("Mr T", value);
    }

    @Test
    public void shouldReturnJoinedStringRepresentationsOfMultipleFieldWithoutExpression() throws Exception {
        // given
        Entity entity = new Entity(1L);
        entity.setField("name", "Mr T");
        entity.setField("age", 33);
        entity.setField("sex", "F");

        FieldDefinition fieldDefinitionName = new FieldDefinition("name");
        FieldDefinition fieldDefinitionAge = new FieldDefinition("age");
        FieldDefinition fieldDefinitionSex = new FieldDefinition("sex");

        ColumnDefinition columnDefinition = new ColumnDefinition("col");
        columnDefinition.setFields(Lists.newArrayList(fieldDefinitionName, fieldDefinitionAge, fieldDefinitionSex));

        // when
        String value = ExpressionUtil.getValue(entity, columnDefinition);

        // then
        assertEquals("Mr T, 33, F", value);
    }

    @Test
    public void shouldUseExpressionForGeneretingValueOfTheSingleFieldColumn() throws Exception {
        // given
        Entity entity = new Entity(1L);
        entity.setField("name", "Mr T");

        FieldDefinition fieldDefinition = new FieldDefinition("name");

        ColumnDefinition columnDefinition = new ColumnDefinition("col");
        columnDefinition.setFields(Lists.newArrayList(fieldDefinition));
        columnDefinition.setExpression("fields['name'].toUpperCase()");

        // when
        String value = ExpressionUtil.getValue(entity, columnDefinition);

        // then
        assertEquals("MR T", value);
    }

    @Test
    public void shouldUseExpressionForGeneretingValueOfTheMultiFieldColumn() throws Exception {
        // given
        Entity entity = new Entity(1L);
        entity.setField("name", "Mr T");
        entity.setField("age", 33);
        entity.setField("sex", "F");

        FieldDefinition fieldDefinitionName = new FieldDefinition("name");
        FieldDefinition fieldDefinitionAge = new FieldDefinition("age");
        FieldDefinition fieldDefinitionSex = new FieldDefinition("sex");

        ColumnDefinition columnDefinition = new ColumnDefinition("col");
        columnDefinition.setFields(Lists.newArrayList(fieldDefinitionName, fieldDefinitionAge, fieldDefinitionSex));
        // columnDefinition
        // .setExpression("fields['name'] + \" -> (\" + (fields['age']+1) + \") -> \" + (fields['sex'] == \"F\" ? \"female\" : \"male\")");
        // columnDefinition.setExpression("name");

        // when
        String value = ExpressionUtil.getValue(entity, columnDefinition);

        // then
        // assertEquals("Mr T -> (34) -> female", value);
    }

}
