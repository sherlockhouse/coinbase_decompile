package com.google.android.gms.internal;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;

public final class zzcbm {
    private static zzcbn<Boolean> zzint = zzcbn.zzb("measurement.service_enabled", true, true);
    private static zzcbn<Boolean> zzinu = zzcbn.zzb("measurement.service_client_enabled", true, true);
    private static zzcbn<Boolean> zzinv = zzcbn.zzb("measurement.log_third_party_store_events_enabled", false, false);
    private static zzcbn<Boolean> zzinw = zzcbn.zzb("measurement.log_installs_enabled", false, false);
    private static zzcbn<Boolean> zzinx = zzcbn.zzb("measurement.log_upgrades_enabled", false, false);
    private static zzcbn<Boolean> zziny = zzcbn.zzb("measurement.log_androidId_enabled", false, false);
    public static zzcbn<Boolean> zzinz = zzcbn.zzb("measurement.upload_dsid_enabled", false, false);
    private static zzcbn<Boolean> zzioa = zzcbn.zzb("measurement.event_sampling_enabled", false, false);
    public static zzcbn<String> zziob = zzcbn.zzi("measurement.log_tag", "FA", "FA-SVC");
    public static zzcbn<Long> zzioc = zzcbn.zzb("measurement.ad_id_cache_time", 10000, 10000);
    public static zzcbn<Long> zziod = zzcbn.zzb("measurement.monitoring.sample_period_millis", 86400000, 86400000);
    public static zzcbn<Long> zzioe = zzcbn.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zzcbn<String> zziof;
    public static zzcbn<String> zziog;
    public static zzcbn<Integer> zzioh = zzcbn.zzm("measurement.upload.max_bundles", 100, 100);
    public static zzcbn<Integer> zzioi = zzcbn.zzm("measurement.upload.max_batch_size", 65536, 65536);
    public static zzcbn<Integer> zzioj = zzcbn.zzm("measurement.upload.max_bundle_size", 65536, 65536);
    public static zzcbn<Integer> zziok = zzcbn.zzm("measurement.upload.max_events_per_bundle", 1000, 1000);
    public static zzcbn<Integer> zziol = zzcbn.zzm("measurement.upload.max_events_per_day", 100000, 100000);
    public static zzcbn<Integer> zziom = zzcbn.zzm("measurement.upload.max_error_events_per_day", 1000, 1000);
    public static zzcbn<Integer> zzion = zzcbn.zzm("measurement.upload.max_public_events_per_day", 50000, 50000);
    public static zzcbn<Integer> zzioo = zzcbn.zzm("measurement.upload.max_conversions_per_day", 500, 500);
    public static zzcbn<Integer> zziop = zzcbn.zzm("measurement.upload.max_realtime_events_per_day", 10, 10);
    public static zzcbn<Integer> zzioq = zzcbn.zzm("measurement.store.max_stored_events_per_app", 100000, 100000);
    public static zzcbn<String> zzior;
    public static zzcbn<Long> zzios = zzcbn.zzb("measurement.upload.backoff_period", 43200000, 43200000);
    public static zzcbn<Long> zziot = zzcbn.zzb("measurement.upload.window_interval", 3600000, 3600000);
    public static zzcbn<Long> zziou = zzcbn.zzb("measurement.upload.interval", 3600000, 3600000);
    public static zzcbn<Long> zziov = zzcbn.zzb("measurement.upload.realtime_upload_interval", 10000, 10000);
    public static zzcbn<Long> zziow = zzcbn.zzb("measurement.upload.debug_upload_interval", 1000, 1000);
    public static zzcbn<Long> zziox = zzcbn.zzb("measurement.upload.minimum_delay", 500, 500);
    public static zzcbn<Long> zzioy = zzcbn.zzb("measurement.alarm_manager.minimum_interval", 60000, 60000);
    public static zzcbn<Long> zzioz = zzcbn.zzb("measurement.upload.stale_data_deletion_interval", 86400000, 86400000);
    public static zzcbn<Long> zzipa = zzcbn.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
    public static zzcbn<Long> zzipb = zzcbn.zzb("measurement.upload.initial_upload_delay_time", 15000, 15000);
    public static zzcbn<Long> zzipc = zzcbn.zzb("measurement.upload.retry_time", 1800000, 1800000);
    public static zzcbn<Integer> zzipd = zzcbn.zzm("measurement.upload.retry_count", 6, 6);
    public static zzcbn<Long> zzipe = zzcbn.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
    public static zzcbn<Integer> zzipf = zzcbn.zzm("measurement.lifetimevalue.max_currency_tracked", 4, 4);
    public static zzcbn<Integer> zzipg = zzcbn.zzm("measurement.audience.filter_result_max_count", Callback.DEFAULT_DRAG_ANIMATION_DURATION, Callback.DEFAULT_DRAG_ANIMATION_DURATION);
    public static zzcbn<Long> zziph = zzcbn.zzb("measurement.service_client.idle_disconnect_millis", 5000, 5000);

    static {
        String str = "https";
        zziof = zzcbn.zzi("measurement.config.url_scheme", str, str);
        str = "app-measurement.com";
        zziog = zzcbn.zzi("measurement.config.url_authority", str, str);
        str = "https://app-measurement.com/a";
        zzior = zzcbn.zzi("measurement.upload.url", str, str);
    }
}
