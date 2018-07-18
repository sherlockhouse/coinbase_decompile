package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Data {
    @SerializedName("access_privacy_rights")
    @Expose
    private Boolean accessPrivacyRights;
    @SerializedName("admin_flags")
    @Expose
    private List<String> adminFlags = new ArrayList();
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("bitcoin_unit")
    @Expose
    private String bitcoinUnit;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("feature_flags")
    @Expose
    private List<String> featureFlags = new ArrayList();
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("merchant")
    @Expose
    private Object merchant;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("native_currency")
    @Expose
    private String nativeCurrency;
    @SerializedName("oauth")
    @Expose
    private Oauth oauth;
    @SerializedName("onboarding_items")
    @Expose
    private List<OnboardingItem> onboardingItems = new ArrayList();
    @SerializedName("personal_details")
    @Expose
    private PersonalDetails personalDetails;
    @SerializedName("profile_bio")
    @Expose
    private String profileBio;
    @SerializedName("profile_location")
    @Expose
    private String profileLocation;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;
    @SerializedName("referral_id")
    @Expose
    private Object referralId;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
    @SerializedName("restrictions")
    @Expose
    private List<String> restrictions = new ArrayList();
    @SerializedName("split_test_groups")
    @Expose
    private List<SplitTest> splitTestGroups = new ArrayList();
    @SerializedName("tiers")
    @Expose
    private Tiers tiers;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("username")
    @Expose
    private String username;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileLocation() {
        return this.profileLocation;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation;
    }

    public String getProfileBio() {
        return this.profileBio;
    }

    public void setProfileBio(String profileBio) {
        this.profileBio = profileBio;
    }

    public String getProfileUrl() {
        return this.profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getNativeCurrency() {
        return this.nativeCurrency;
    }

    public void setNativeCurrency(String nativeCurrency) {
        this.nativeCurrency = nativeCurrency;
    }

    public String getBitcoinUnit() {
        return this.bitcoinUnit;
    }

    public void setBitcoinUnit(String bitcoinUnit) {
        this.bitcoinUnit = bitcoinUnit;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getRestrictions() {
        return this.restrictions;
    }

    public void setRestrictions(List<String> restrictions) {
        this.restrictions = restrictions;
    }

    public List<String> getFeatureFlags() {
        return this.featureFlags;
    }

    public void setFeatureFlags(List<String> featureFlags) {
        this.featureFlags = featureFlags;
    }

    public List<SplitTest> getSplitTestGroups() {
        return this.splitTestGroups;
    }

    public void setSplitTestGroups(List<SplitTest> splitTestGroups) {
        this.splitTestGroups = splitTestGroups;
    }

    public List<String> getAdminFlags() {
        return this.adminFlags;
    }

    public void setAdminFlags(List<String> adminFlags) {
        this.adminFlags = adminFlags;
    }

    public List<OnboardingItem> getOnboardingItems() {
        return this.onboardingItems;
    }

    public void setOnboardingItems(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    public PersonalDetails getPersonalDetails() {
        return this.personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public Tiers getTiers() {
        return this.tiers;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public Object getMerchant() {
        return this.merchant;
    }

    public void setMerchant(Object merchant) {
        this.merchant = merchant;
    }

    public Oauth getOauth() {
        return this.oauth;
    }

    public void setOauth(Oauth oauth) {
        this.oauth = oauth;
    }

    public Object getReferralId() {
        return this.referralId;
    }

    public void setReferralId(Object referralId) {
        this.referralId = referralId;
    }

    public Boolean getAccessPrivacyRights() {
        return this.accessPrivacyRights;
    }

    public void setAccessPrivacyRights(Boolean accessPrivacyRights) {
        this.accessPrivacyRights = accessPrivacyRights;
    }
}
