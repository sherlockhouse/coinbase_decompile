package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Message extends C$AutoValue_Message {

    public static final class GsonTypeAdapter extends TypeAdapter<Message> {
        private final TypeAdapter<String> actionAdapter;
        private String defaultAction = null;
        private String defaultDescription = null;
        private String defaultImage = null;
        private String defaultLink = null;
        private String defaultMinVersion = null;
        private String defaultTitle = null;
        private String defaultVersion = null;
        private VersionRange defaultVersionRange = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> imageAdapter;
        private final TypeAdapter<String> linkAdapter;
        private final TypeAdapter<String> minVersionAdapter;
        private final TypeAdapter<String> titleAdapter;
        private final TypeAdapter<String> versionAdapter;
        private final TypeAdapter<VersionRange> versionRangeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.imageAdapter = gson.getAdapter(String.class);
            this.versionRangeAdapter = gson.getAdapter(VersionRange.class);
            this.titleAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.actionAdapter = gson.getAdapter(String.class);
            this.versionAdapter = gson.getAdapter(String.class);
            this.minVersionAdapter = gson.getAdapter(String.class);
            this.linkAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultImage(String defaultImage) {
            this.defaultImage = defaultImage;
            return this;
        }

        public GsonTypeAdapter setDefaultVersionRange(VersionRange defaultVersionRange) {
            this.defaultVersionRange = defaultVersionRange;
            return this;
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultAction(String defaultAction) {
            this.defaultAction = defaultAction;
            return this;
        }

        public GsonTypeAdapter setDefaultVersion(String defaultVersion) {
            this.defaultVersion = defaultVersion;
            return this;
        }

        public GsonTypeAdapter setDefaultMinVersion(String defaultMinVersion) {
            this.defaultMinVersion = defaultMinVersion;
            return this;
        }

        public GsonTypeAdapter setDefaultLink(String defaultLink) {
            this.defaultLink = defaultLink;
            return this;
        }

        public void write(JsonWriter jsonWriter, Message object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("image");
            this.imageAdapter.write(jsonWriter, object.getImage());
            jsonWriter.name("version_range");
            this.versionRangeAdapter.write(jsonWriter, object.getVersionRange());
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name(ApiConstants.ACTION);
            this.actionAdapter.write(jsonWriter, object.getAction());
            jsonWriter.name("version");
            this.versionAdapter.write(jsonWriter, object.getVersion());
            jsonWriter.name("min_version");
            this.minVersionAdapter.write(jsonWriter, object.getMinVersion());
            jsonWriter.name("link");
            this.linkAdapter.write(jsonWriter, object.getLink());
            jsonWriter.endObject();
        }

        public Message read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String image = this.defaultImage;
            VersionRange versionRange = this.defaultVersionRange;
            String title = this.defaultTitle;
            String description = this.defaultDescription;
            String action = this.defaultAction;
            String version = this.defaultVersion;
            String minVersion = this.defaultMinVersion;
            String link = this.defaultLink;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -1422950858:
                            if (_name.equals(ApiConstants.ACTION)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -294914069:
                            if (_name.equals("min_version")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 3321850:
                            if (_name.equals("link")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 100313435:
                            if (_name.equals("image")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 351608024:
                            if (_name.equals("version")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 1115862806:
                            if (_name.equals("version_range")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            image = (String) this.imageAdapter.read(jsonReader);
                            break;
                        case 1:
                            versionRange = (VersionRange) this.versionRangeAdapter.read(jsonReader);
                            break;
                        case 2:
                            title = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 3:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 4:
                            action = (String) this.actionAdapter.read(jsonReader);
                            break;
                        case 5:
                            version = (String) this.versionAdapter.read(jsonReader);
                            break;
                        case 6:
                            minVersion = (String) this.minVersionAdapter.read(jsonReader);
                            break;
                        case 7:
                            link = (String) this.linkAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Message(image, versionRange, title, description, action, version, minVersion, link);
        }
    }

    AutoValue_Message(String image, VersionRange versionRange, String title, String description, String action, String version, String minVersion, String link) {
        super(image, versionRange, title, description, action, version, minVersion, link);
    }
}
