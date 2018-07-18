package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public class Barcode extends zzbck {
    public static final Creator<Barcode> CREATOR = new zzb();
    public CalendarEvent calendarEvent;
    public ContactInfo contactInfo;
    public Point[] cornerPoints;
    public String displayValue;
    public DriverLicense driverLicense;
    public Email email;
    public int format;
    public GeoPoint geoPoint;
    public Phone phone;
    public String rawValue;
    public Sms sms;
    public UrlBookmark url;
    public int valueFormat;
    public WiFi wifi;

    public static class Address extends zzbck {
        public static final Creator<Address> CREATOR = new zza();
        public String[] addressLines;
        public int type;

        public Address(int i, String[] strArr) {
            this.type = i;
            this.addressLines = strArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zzc(parcel, 2, this.type);
            zzbcn.zza(parcel, 3, this.addressLines, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class CalendarDateTime extends zzbck {
        public static final Creator<CalendarDateTime> CREATOR = new zzd();
        public int day;
        public int hours;
        public boolean isUtc;
        public int minutes;
        public int month;
        public String rawValue;
        public int seconds;
        public int year;

        public CalendarDateTime(int i, int i2, int i3, int i4, int i5, int i6, boolean z, String str) {
            this.year = i;
            this.month = i2;
            this.day = i3;
            this.hours = i4;
            this.minutes = i5;
            this.seconds = i6;
            this.isUtc = z;
            this.rawValue = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zzc(parcel, 2, this.year);
            zzbcn.zzc(parcel, 3, this.month);
            zzbcn.zzc(parcel, 4, this.day);
            zzbcn.zzc(parcel, 5, this.hours);
            zzbcn.zzc(parcel, 6, this.minutes);
            zzbcn.zzc(parcel, 7, this.seconds);
            zzbcn.zza(parcel, 8, this.isUtc);
            zzbcn.zza(parcel, 9, this.rawValue, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class CalendarEvent extends zzbck {
        public static final Creator<CalendarEvent> CREATOR = new zze();
        public String description;
        public CalendarDateTime end;
        public String location;
        public String organizer;
        public CalendarDateTime start;
        public String status;
        public String summary;

        public CalendarEvent(String str, String str2, String str3, String str4, String str5, CalendarDateTime calendarDateTime, CalendarDateTime calendarDateTime2) {
            this.summary = str;
            this.description = str2;
            this.location = str3;
            this.organizer = str4;
            this.status = str5;
            this.start = calendarDateTime;
            this.end = calendarDateTime2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.summary, false);
            zzbcn.zza(parcel, 3, this.description, false);
            zzbcn.zza(parcel, 4, this.location, false);
            zzbcn.zza(parcel, 5, this.organizer, false);
            zzbcn.zza(parcel, 6, this.status, false);
            zzbcn.zza(parcel, 7, this.start, i, false);
            zzbcn.zza(parcel, 8, this.end, i, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class ContactInfo extends zzbck {
        public static final Creator<ContactInfo> CREATOR = new zzf();
        public Address[] addresses;
        public Email[] emails;
        public PersonName name;
        public String organization;
        public Phone[] phones;
        public String title;
        public String[] urls;

        public ContactInfo(PersonName personName, String str, String str2, Phone[] phoneArr, Email[] emailArr, String[] strArr, Address[] addressArr) {
            this.name = personName;
            this.organization = str;
            this.title = str2;
            this.phones = phoneArr;
            this.emails = emailArr;
            this.urls = strArr;
            this.addresses = addressArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.name, i, false);
            zzbcn.zza(parcel, 3, this.organization, false);
            zzbcn.zza(parcel, 4, this.title, false);
            zzbcn.zza(parcel, 5, this.phones, i, false);
            zzbcn.zza(parcel, 6, this.emails, i, false);
            zzbcn.zza(parcel, 7, this.urls, false);
            zzbcn.zza(parcel, 8, this.addresses, i, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class DriverLicense extends zzbck {
        public static final Creator<DriverLicense> CREATOR = new zzg();
        public String addressCity;
        public String addressState;
        public String addressStreet;
        public String addressZip;
        public String birthDate;
        public String documentType;
        public String expiryDate;
        public String firstName;
        public String gender;
        public String issueDate;
        public String issuingCountry;
        public String lastName;
        public String licenseNumber;
        public String middleName;

        public DriverLicense(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
            this.documentType = str;
            this.firstName = str2;
            this.middleName = str3;
            this.lastName = str4;
            this.gender = str5;
            this.addressStreet = str6;
            this.addressCity = str7;
            this.addressState = str8;
            this.addressZip = str9;
            this.licenseNumber = str10;
            this.issueDate = str11;
            this.expiryDate = str12;
            this.birthDate = str13;
            this.issuingCountry = str14;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.documentType, false);
            zzbcn.zza(parcel, 3, this.firstName, false);
            zzbcn.zza(parcel, 4, this.middleName, false);
            zzbcn.zza(parcel, 5, this.lastName, false);
            zzbcn.zza(parcel, 6, this.gender, false);
            zzbcn.zza(parcel, 7, this.addressStreet, false);
            zzbcn.zza(parcel, 8, this.addressCity, false);
            zzbcn.zza(parcel, 9, this.addressState, false);
            zzbcn.zza(parcel, 10, this.addressZip, false);
            zzbcn.zza(parcel, 11, this.licenseNumber, false);
            zzbcn.zza(parcel, 12, this.issueDate, false);
            zzbcn.zza(parcel, 13, this.expiryDate, false);
            zzbcn.zza(parcel, 14, this.birthDate, false);
            zzbcn.zza(parcel, 15, this.issuingCountry, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class Email extends zzbck {
        public static final Creator<Email> CREATOR = new zzh();
        public String address;
        public String body;
        public String subject;
        public int type;

        public Email(int i, String str, String str2, String str3) {
            this.type = i;
            this.address = str;
            this.subject = str2;
            this.body = str3;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zzc(parcel, 2, this.type);
            zzbcn.zza(parcel, 3, this.address, false);
            zzbcn.zza(parcel, 4, this.subject, false);
            zzbcn.zza(parcel, 5, this.body, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class GeoPoint extends zzbck {
        public static final Creator<GeoPoint> CREATOR = new zzi();
        public double lat;
        public double lng;

        public GeoPoint(double d, double d2) {
            this.lat = d;
            this.lng = d2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.lat);
            zzbcn.zza(parcel, 3, this.lng);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class PersonName extends zzbck {
        public static final Creator<PersonName> CREATOR = new zzj();
        public String first;
        public String formattedName;
        public String last;
        public String middle;
        public String prefix;
        public String pronunciation;
        public String suffix;

        public PersonName(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.formattedName = str;
            this.pronunciation = str2;
            this.prefix = str3;
            this.first = str4;
            this.middle = str5;
            this.last = str6;
            this.suffix = str7;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.formattedName, false);
            zzbcn.zza(parcel, 3, this.pronunciation, false);
            zzbcn.zza(parcel, 4, this.prefix, false);
            zzbcn.zza(parcel, 5, this.first, false);
            zzbcn.zza(parcel, 6, this.middle, false);
            zzbcn.zza(parcel, 7, this.last, false);
            zzbcn.zza(parcel, 8, this.suffix, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class Phone extends zzbck {
        public static final Creator<Phone> CREATOR = new zzk();
        public String number;
        public int type;

        public Phone(int i, String str) {
            this.type = i;
            this.number = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zzc(parcel, 2, this.type);
            zzbcn.zza(parcel, 3, this.number, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class Sms extends zzbck {
        public static final Creator<Sms> CREATOR = new zzl();
        public String message;
        public String phoneNumber;

        public Sms(String str, String str2) {
            this.message = str;
            this.phoneNumber = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.message, false);
            zzbcn.zza(parcel, 3, this.phoneNumber, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class UrlBookmark extends zzbck {
        public static final Creator<UrlBookmark> CREATOR = new zzm();
        public String title;
        public String url;

        public UrlBookmark(String str, String str2) {
            this.title = str;
            this.url = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.title, false);
            zzbcn.zza(parcel, 3, this.url, false);
            zzbcn.zzai(parcel, zze);
        }
    }

    public static class WiFi extends zzbck {
        public static final Creator<WiFi> CREATOR = new zzn();
        public int encryptionType;
        public String password;
        public String ssid;

        public WiFi(String str, String str2, int i) {
            this.ssid = str;
            this.password = str2;
            this.encryptionType = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbcn.zze(parcel);
            zzbcn.zza(parcel, 2, this.ssid, false);
            zzbcn.zza(parcel, 3, this.password, false);
            zzbcn.zzc(parcel, 4, this.encryptionType);
            zzbcn.zzai(parcel, zze);
        }
    }

    public Barcode(int i, String str, String str2, int i2, Point[] pointArr, Email email, Phone phone, Sms sms, WiFi wiFi, UrlBookmark urlBookmark, GeoPoint geoPoint, CalendarEvent calendarEvent, ContactInfo contactInfo, DriverLicense driverLicense) {
        this.format = i;
        this.rawValue = str;
        this.displayValue = str2;
        this.valueFormat = i2;
        this.cornerPoints = pointArr;
        this.email = email;
        this.phone = phone;
        this.sms = sms;
        this.wifi = wiFi;
        this.url = urlBookmark;
        this.geoPoint = geoPoint;
        this.calendarEvent = calendarEvent;
        this.contactInfo = contactInfo;
        this.driverLicense = driverLicense;
    }

    public Rect getBoundingBox() {
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : this.cornerPoints) {
            i4 = Math.min(i4, point.x);
            i3 = Math.max(i3, point.x);
            i = Math.min(i, point.y);
            i2 = Math.max(i2, point.y);
        }
        return new Rect(i4, i, i3, i2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 2, this.format);
        zzbcn.zza(parcel, 3, this.rawValue, false);
        zzbcn.zza(parcel, 4, this.displayValue, false);
        zzbcn.zzc(parcel, 5, this.valueFormat);
        zzbcn.zza(parcel, 6, this.cornerPoints, i, false);
        zzbcn.zza(parcel, 7, this.email, i, false);
        zzbcn.zza(parcel, 8, this.phone, i, false);
        zzbcn.zza(parcel, 9, this.sms, i, false);
        zzbcn.zza(parcel, 10, this.wifi, i, false);
        zzbcn.zza(parcel, 11, this.url, i, false);
        zzbcn.zza(parcel, 12, this.geoPoint, i, false);
        zzbcn.zza(parcel, 13, this.calendarEvent, i, false);
        zzbcn.zza(parcel, 14, this.contactInfo, i, false);
        zzbcn.zza(parcel, 15, this.driverLicense, i, false);
        zzbcn.zzai(parcel, zze);
    }
}
