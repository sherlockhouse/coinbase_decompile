package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators.Base;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleValueInstantiators extends Base implements Serializable {
    protected HashMap<ClassKey, ValueInstantiator> _classMappings = new HashMap();

    public ValueInstantiator findValueInstantiator(DeserializationConfig config, BeanDescription beanDesc, ValueInstantiator defaultInstantiator) {
        ValueInstantiator inst = (ValueInstantiator) this._classMappings.get(new ClassKey(beanDesc.getBeanClass()));
        return inst == null ? defaultInstantiator : inst;
    }
}
