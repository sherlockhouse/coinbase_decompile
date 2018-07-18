package com.coinbase.api.internal.models.idology;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Job extends C$AutoValue_Job {

    public static final class GsonTypeAdapter extends TypeAdapter<Job> {
        private String defaultEmployer = null;
        private String defaultTitle = null;
        private String defaultTitleDesc = null;
        private final TypeAdapter<String> employerAdapter;
        private final TypeAdapter<String> titleAdapter;
        private final TypeAdapter<String> titleDescAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.employerAdapter = gson.getAdapter(String.class);
            this.titleAdapter = gson.getAdapter(String.class);
            this.titleDescAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultEmployer(String defaultEmployer) {
            this.defaultEmployer = defaultEmployer;
            return this;
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultTitleDesc(String defaultTitleDesc) {
            this.defaultTitleDesc = defaultTitleDesc;
            return this;
        }

        public void write(JsonWriter jsonWriter, Job object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("employer");
            this.employerAdapter.write(jsonWriter, object.getEmployer());
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("title_desc");
            this.titleDescAdapter.write(jsonWriter, object.getTitleDesc());
            jsonWriter.endObject();
        }

        public Job read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String employer = this.defaultEmployer;
            String title = this.defaultTitle;
            String titleDesc = this.defaultTitleDesc;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1773843432:
                            if (_name.equals("title_desc")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1193469627:
                            if (_name.equals("employer")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            employer = (String) this.employerAdapter.read(jsonReader);
                            break;
                        case 1:
                            title = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 2:
                            titleDesc = (String) this.titleDescAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Job(employer, title, titleDesc);
        }
    }

    AutoValue_Job(String employer, String title, String titleDesc) {
        super(employer, title, titleDesc);
    }
}
