package com.bumptech.glide.load.data;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);

    static {
        URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*", 1);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#/photo", 2);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#", 3);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#/display_photo", 4);
    }

    public StreamLocalUriFetcher(ContentResolver resolver, Uri uri) {
        super(resolver, uri);
    }

    protected InputStream loadResource(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        InputStream inputStream = loadResourceFromUri(uri, contentResolver);
        if (inputStream != null) {
            return inputStream;
        }
        throw new FileNotFoundException("InputStream is null for " + uri);
    }

    private InputStream loadResourceFromUri(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        switch (URI_MATCHER.match(uri)) {
            case 1:
                uri = Contacts.lookupContact(contentResolver, uri);
                if (uri != null) {
                    return openContactPhotoInputStream(contentResolver, uri);
                }
                throw new FileNotFoundException("Contact cannot be found");
            case 3:
                return openContactPhotoInputStream(contentResolver, uri);
            default:
                return contentResolver.openInputStream(uri);
        }
    }

    @TargetApi(14)
    private InputStream openContactPhotoInputStream(ContentResolver contentResolver, Uri contactUri) {
        return Contacts.openContactPhotoInputStream(contentResolver, contactUri, true);
    }

    protected void close(InputStream data) throws IOException {
        data.close();
    }

    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }
}
