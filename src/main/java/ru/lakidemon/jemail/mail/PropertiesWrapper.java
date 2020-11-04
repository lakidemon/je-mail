package ru.lakidemon.jemail.mail;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

public interface PropertiesWrapper {

    PropertiesWrapper put(String key, Object value);

    PropertiesWrapper getParent();

    default PropertiesWrapper subSection(String name) {
        return new SubSection(name, this);
    }

    Properties getProperties();

    @RequiredArgsConstructor
    class SubSection implements PropertiesWrapper {
        private final String name;
        @Getter
        private final PropertiesWrapper parent;

        @Override
        public PropertiesWrapper put(String key, Object value) {
            parent.put(name + "." + key, value);
            return this;
        }

        @Override
        public Properties getProperties() {
            PropertiesWrapper section = parent;
            while (!(section instanceof RootSection)) {
                section = section.getParent();
            }
            return section.getProperties();
        }
    }

    @RequiredArgsConstructor
    class RootSection implements PropertiesWrapper {
        private final String name;
        @Getter
        private final @NonNull Properties properties;

        public RootSection(String name) {
            this(name, new Properties());
        }

        @Override
        public PropertiesWrapper getParent() {
            throw new IllegalStateException("No parent for root");
        }

        public PropertiesWrapper put(String key, Object value) {
            this.properties.put(name + "." + key, value);
            return this;
        }
    }

    static PropertiesWrapper mailWrapper() {
        return forName("mail");
    }

    static PropertiesWrapper forName(String root) {
        return new RootSection(root);
    }
}
