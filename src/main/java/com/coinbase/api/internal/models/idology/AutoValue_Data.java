package com.coinbase.api.internal.models.idology;

import com.coinbase.android.db.TransactionORM;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.idology.Data.Status;
import com.coinbase.v2.models.user.DateOfBirth;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<Address> addressAdapter;
        private final TypeAdapter<String> createdAtAdapter;
        private final TypeAdapter<Job> currentJobAdapter;
        private final TypeAdapter<DateOfBirth> dateOfBirthAdapter;
        private Address defaultAddress = null;
        private String defaultCreatedAt = null;
        private Job defaultCurrentJob = null;
        private DateOfBirth defaultDateOfBirth = null;
        private String defaultFallbackToRestriction = null;
        private String defaultFirstName = null;
        private Job defaultFormerJob = null;
        private String defaultId = null;
        private boolean defaultInformationalOnly = false;
        private String defaultLastName = null;
        private String defaultMethod = null;
        private String defaultMobilePhoneNumber = null;
        private String defaultPrimarySourceOfFunds = null;
        private List<Question> defaultQuestions = null;
        private String defaultSsnLast4 = null;
        private Status defaultStatus = null;
        private String defaultUpdatedAt = null;
        private List<String> defaultUsesCoinbaseFor = null;
        private final TypeAdapter<String> fallbackToRestrictionAdapter;
        private final TypeAdapter<String> firstNameAdapter;
        private final TypeAdapter<Job> formerJobAdapter;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<Boolean> informationalOnlyAdapter;
        private final TypeAdapter<String> lastNameAdapter;
        private final TypeAdapter<String> methodAdapter;
        private final TypeAdapter<String> mobilePhoneNumberAdapter;
        private final TypeAdapter<String> primarySourceOfFundsAdapter;
        private final TypeAdapter<List<Question>> questionsAdapter;
        private final TypeAdapter<String> ssnLast4Adapter;
        private final TypeAdapter<Status> statusAdapter;
        private final TypeAdapter<String> updatedAtAdapter;
        private final TypeAdapter<List<String>> usesCoinbaseForAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.createdAtAdapter = gson.getAdapter(String.class);
            this.updatedAtAdapter = gson.getAdapter(String.class);
            this.statusAdapter = gson.getAdapter(Status.class);
            this.methodAdapter = gson.getAdapter(String.class);
            this.informationalOnlyAdapter = gson.getAdapter(Boolean.class);
            this.firstNameAdapter = gson.getAdapter(String.class);
            this.lastNameAdapter = gson.getAdapter(String.class);
            this.addressAdapter = gson.getAdapter(Address.class);
            this.dateOfBirthAdapter = gson.getAdapter(DateOfBirth.class);
            this.mobilePhoneNumberAdapter = gson.getAdapter(String.class);
            this.usesCoinbaseForAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.primarySourceOfFundsAdapter = gson.getAdapter(String.class);
            this.currentJobAdapter = gson.getAdapter(Job.class);
            this.formerJobAdapter = gson.getAdapter(Job.class);
            this.ssnLast4Adapter = gson.getAdapter(String.class);
            this.questionsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Question.class));
            this.fallbackToRestrictionAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultCreatedAt(String defaultCreatedAt) {
            this.defaultCreatedAt = defaultCreatedAt;
            return this;
        }

        public GsonTypeAdapter setDefaultUpdatedAt(String defaultUpdatedAt) {
            this.defaultUpdatedAt = defaultUpdatedAt;
            return this;
        }

        public GsonTypeAdapter setDefaultStatus(Status defaultStatus) {
            this.defaultStatus = defaultStatus;
            return this;
        }

        public GsonTypeAdapter setDefaultMethod(String defaultMethod) {
            this.defaultMethod = defaultMethod;
            return this;
        }

        public GsonTypeAdapter setDefaultInformationalOnly(boolean defaultInformationalOnly) {
            this.defaultInformationalOnly = defaultInformationalOnly;
            return this;
        }

        public GsonTypeAdapter setDefaultFirstName(String defaultFirstName) {
            this.defaultFirstName = defaultFirstName;
            return this;
        }

        public GsonTypeAdapter setDefaultLastName(String defaultLastName) {
            this.defaultLastName = defaultLastName;
            return this;
        }

        public GsonTypeAdapter setDefaultAddress(Address defaultAddress) {
            this.defaultAddress = defaultAddress;
            return this;
        }

        public GsonTypeAdapter setDefaultDateOfBirth(DateOfBirth defaultDateOfBirth) {
            this.defaultDateOfBirth = defaultDateOfBirth;
            return this;
        }

        public GsonTypeAdapter setDefaultMobilePhoneNumber(String defaultMobilePhoneNumber) {
            this.defaultMobilePhoneNumber = defaultMobilePhoneNumber;
            return this;
        }

        public GsonTypeAdapter setDefaultUsesCoinbaseFor(List<String> defaultUsesCoinbaseFor) {
            this.defaultUsesCoinbaseFor = defaultUsesCoinbaseFor;
            return this;
        }

        public GsonTypeAdapter setDefaultPrimarySourceOfFunds(String defaultPrimarySourceOfFunds) {
            this.defaultPrimarySourceOfFunds = defaultPrimarySourceOfFunds;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrentJob(Job defaultCurrentJob) {
            this.defaultCurrentJob = defaultCurrentJob;
            return this;
        }

        public GsonTypeAdapter setDefaultFormerJob(Job defaultFormerJob) {
            this.defaultFormerJob = defaultFormerJob;
            return this;
        }

        public GsonTypeAdapter setDefaultSsnLast4(String defaultSsnLast4) {
            this.defaultSsnLast4 = defaultSsnLast4;
            return this;
        }

        public GsonTypeAdapter setDefaultQuestions(List<Question> defaultQuestions) {
            this.defaultQuestions = defaultQuestions;
            return this;
        }

        public GsonTypeAdapter setDefaultFallbackToRestriction(String defaultFallbackToRestriction) {
            this.defaultFallbackToRestriction = defaultFallbackToRestriction;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name(TransactionORM.COLUMN_CREATED_AT);
            this.createdAtAdapter.write(jsonWriter, object.getCreatedAt());
            jsonWriter.name("updated_at");
            this.updatedAtAdapter.write(jsonWriter, object.getUpdatedAt());
            jsonWriter.name("status");
            this.statusAdapter.write(jsonWriter, object.getStatus());
            jsonWriter.name("method");
            this.methodAdapter.write(jsonWriter, object.getMethod());
            jsonWriter.name("informational_only");
            this.informationalOnlyAdapter.write(jsonWriter, Boolean.valueOf(object.getInformationalOnly()));
            jsonWriter.name(ApiConstants.FIRST_NAME);
            this.firstNameAdapter.write(jsonWriter, object.getFirstName());
            jsonWriter.name(ApiConstants.LAST_NAME);
            this.lastNameAdapter.write(jsonWriter, object.getLastName());
            jsonWriter.name("address");
            this.addressAdapter.write(jsonWriter, object.getAddress());
            jsonWriter.name("date_of_birth");
            this.dateOfBirthAdapter.write(jsonWriter, object.getDateOfBirth());
            jsonWriter.name("mobile_phone_number");
            this.mobilePhoneNumberAdapter.write(jsonWriter, object.getMobilePhoneNumber());
            jsonWriter.name(ApiConstants.USES_COINBASE_FOR);
            this.usesCoinbaseForAdapter.write(jsonWriter, object.getUsesCoinbaseFor());
            jsonWriter.name(ApiConstants.PRIMARY_SOURCE_OF_FUNDS);
            this.primarySourceOfFundsAdapter.write(jsonWriter, object.getPrimarySourceOfFunds());
            jsonWriter.name("current_job");
            this.currentJobAdapter.write(jsonWriter, object.getCurrentJob());
            jsonWriter.name("former_job");
            this.formerJobAdapter.write(jsonWriter, object.getFormerJob());
            jsonWriter.name(ApiConstants.SSN_LAST4);
            this.ssnLast4Adapter.write(jsonWriter, object.getSsnLast4());
            jsonWriter.name("questions");
            this.questionsAdapter.write(jsonWriter, object.getQuestions());
            jsonWriter.name("fallback_to_restriction");
            this.fallbackToRestrictionAdapter.write(jsonWriter, object.getFallbackToRestriction());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String createdAt = this.defaultCreatedAt;
            String updatedAt = this.defaultUpdatedAt;
            Status status = this.defaultStatus;
            String method = this.defaultMethod;
            boolean informationalOnly = this.defaultInformationalOnly;
            String firstName = this.defaultFirstName;
            String lastName = this.defaultLastName;
            Address address = this.defaultAddress;
            DateOfBirth dateOfBirth = this.defaultDateOfBirth;
            String mobilePhoneNumber = this.defaultMobilePhoneNumber;
            List<String> usesCoinbaseFor = this.defaultUsesCoinbaseFor;
            String primarySourceOfFunds = this.defaultPrimarySourceOfFunds;
            Job currentJob = this.defaultCurrentJob;
            Job formerJob = this.defaultFormerJob;
            String ssnLast4 = this.defaultSsnLast4;
            List<Question> questions = this.defaultQuestions;
            String fallbackToRestriction = this.defaultFallbackToRestriction;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1961471852:
                            if (_name.equals("informational_only")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -1782234803:
                            if (_name.equals("questions")) {
                                obj = 16;
                                break;
                            }
                            break;
                        case -1780960329:
                            if (_name.equals("mobile_phone_number")) {
                                obj = 10;
                                break;
                            }
                            break;
                        case -1475436819:
                            if (_name.equals(ApiConstants.SSN_LAST4)) {
                                obj = 15;
                                break;
                            }
                            break;
                        case -1463318145:
                            if (_name.equals(ApiConstants.USES_COINBASE_FOR)) {
                                obj = 11;
                                break;
                            }
                            break;
                        case -1181815352:
                            if (_name.equals("date_of_birth")) {
                                obj = 9;
                                break;
                            }
                            break;
                        case -1147692044:
                            if (_name.equals("address")) {
                                obj = 8;
                                break;
                            }
                            break;
                        case -1077554975:
                            if (_name.equals("method")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -892481550:
                            if (_name.equals("status")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -620656891:
                            if (_name.equals("fallback_to_restriction")) {
                                obj = 17;
                                break;
                            }
                            break;
                        case -295464393:
                            if (_name.equals("updated_at")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -257064627:
                            if (_name.equals(ApiConstants.PRIMARY_SOURCE_OF_FUNDS)) {
                                obj = 12;
                                break;
                            }
                            break;
                        case -160985414:
                            if (_name.equals(ApiConstants.FIRST_NAME)) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 436806383:
                            if (_name.equals("former_job")) {
                                obj = 14;
                                break;
                            }
                            break;
                        case 601564151:
                            if (_name.equals("current_job")) {
                                obj = 13;
                                break;
                            }
                            break;
                        case 1369680106:
                            if (_name.equals(TransactionORM.COLUMN_CREATED_AT)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 2013122196:
                            if (_name.equals(ApiConstants.LAST_NAME)) {
                                obj = 7;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            createdAt = (String) this.createdAtAdapter.read(jsonReader);
                            break;
                        case 2:
                            updatedAt = (String) this.updatedAtAdapter.read(jsonReader);
                            break;
                        case 3:
                            status = (Status) this.statusAdapter.read(jsonReader);
                            break;
                        case 4:
                            method = (String) this.methodAdapter.read(jsonReader);
                            break;
                        case 5:
                            informationalOnly = ((Boolean) this.informationalOnlyAdapter.read(jsonReader)).booleanValue();
                            break;
                        case 6:
                            firstName = (String) this.firstNameAdapter.read(jsonReader);
                            break;
                        case 7:
                            lastName = (String) this.lastNameAdapter.read(jsonReader);
                            break;
                        case 8:
                            address = (Address) this.addressAdapter.read(jsonReader);
                            break;
                        case 9:
                            dateOfBirth = (DateOfBirth) this.dateOfBirthAdapter.read(jsonReader);
                            break;
                        case 10:
                            mobilePhoneNumber = (String) this.mobilePhoneNumberAdapter.read(jsonReader);
                            break;
                        case 11:
                            usesCoinbaseFor = (List) this.usesCoinbaseForAdapter.read(jsonReader);
                            break;
                        case 12:
                            primarySourceOfFunds = (String) this.primarySourceOfFundsAdapter.read(jsonReader);
                            break;
                        case 13:
                            currentJob = (Job) this.currentJobAdapter.read(jsonReader);
                            break;
                        case 14:
                            formerJob = (Job) this.formerJobAdapter.read(jsonReader);
                            break;
                        case 15:
                            ssnLast4 = (String) this.ssnLast4Adapter.read(jsonReader);
                            break;
                        case 16:
                            questions = (List) this.questionsAdapter.read(jsonReader);
                            break;
                        case 17:
                            fallbackToRestriction = (String) this.fallbackToRestrictionAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(id, createdAt, updatedAt, status, method, informationalOnly, firstName, lastName, address, dateOfBirth, mobilePhoneNumber, usesCoinbaseFor, primarySourceOfFunds, currentJob, formerJob, ssnLast4, questions, fallbackToRestriction);
        }
    }

    AutoValue_Data(String id, String createdAt, String updatedAt, Status status, String method, boolean informationalOnly, String firstName, String lastName, Address address, DateOfBirth dateOfBirth, String mobilePhoneNumber, List<String> usesCoinbaseFor, String primarySourceOfFunds, Job currentJob, Job formerJob, String ssnLast4, List<Question> questions, String fallbackToRestriction) {
        super(id, createdAt, updatedAt, status, method, informationalOnly, firstName, lastName, address, dateOfBirth, mobilePhoneNumber, usesCoinbaseFor, primarySourceOfFunds, currentJob, formerJob, ssnLast4, questions, fallbackToRestriction);
    }
}
