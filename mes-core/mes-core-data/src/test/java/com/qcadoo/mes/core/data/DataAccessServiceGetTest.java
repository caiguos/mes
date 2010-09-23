package com.qcadoo.mes.core.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import org.junit.Test;

import com.qcadoo.mes.beans.test.TestSimpleDatabaseObject;
import com.qcadoo.mes.core.data.beans.Entity;

public final class DataAccessServiceGetTest extends DataAccessTest {

    @Test(expected = IllegalStateException.class)
    public void shouldFailIfThereIsNoClassForGivenEntityName() throws Exception {
        // given
        dataDefinition.setFullyQualifiedClassName("not.existing.class.Name");

        // when
        dataDefinition.get(1L);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailIfIdIsNull() throws Exception {
        // when
        dataDefinition.get(null);
    }

    @Test
    public void shouldReturnValidEntity() throws Exception {
        // given
        TestSimpleDatabaseObject simpleDatabaseObject = new TestSimpleDatabaseObject();
        simpleDatabaseObject.setId(1L);
        simpleDatabaseObject.setName("Mr T");
        simpleDatabaseObject.setAge(66);

        given(criteria.uniqueResult()).willReturn(simpleDatabaseObject);

        // when
        Entity entity = dataDefinition.get(1L);

        // then
        assertEquals(1L, entity.getId().longValue());
        assertEquals("Mr T", entity.getField("name"));
        assertEquals(66, entity.getField("age"));
    }

    @Test
    public void shouldNotFailIfFieldTypeIsNotValid() throws Exception {
        // given
        fieldDefinitionName.withType(fieldTypeFactory.integerType());

        TestSimpleDatabaseObject simpleDatabaseObject = new TestSimpleDatabaseObject();
        simpleDatabaseObject.setId(1L);
        simpleDatabaseObject.setName("Mr T");
        simpleDatabaseObject.setAge(66);

        given(criteria.uniqueResult()).willReturn(simpleDatabaseObject);

        // when
        dataDefinition.get(1L);
    }

    public void shouldReturnNullIfEntityNotFound() throws Exception {
        // given
        dataDefinition.setFullyQualifiedClassName(TestSimpleDatabaseObject.class.getCanonicalName());

        given(criteria.uniqueResult()).willReturn(null);

        // when
        Entity entity = dataDefinition.get(1L);

        // then
        assertNull(entity);
    }

}
