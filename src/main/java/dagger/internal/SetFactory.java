package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List<Provider<Collection<T>>> collectionProviders;
    private final List<Provider<T>> individualProviders;

    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = (!SetFactory.class.desiredAssertionStatus());
        private final List<Provider<Collection<T>>> collectionProviders;
        private final List<Provider<T>> individualProviders;

        private Builder(int individualProviderSize, int collectionProviderSize) {
            this.individualProviders = DaggerCollections.presizedList(individualProviderSize);
            this.collectionProviders = DaggerCollections.presizedList(collectionProviderSize);
        }

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> collectionProvider) {
            if ($assertionsDisabled || collectionProvider != null) {
                this.collectionProviders.add(collectionProvider);
                return this;
            }
            throw new AssertionError("Codegen error? Null provider");
        }

        public SetFactory<T> build() {
            if (!$assertionsDisabled && DaggerCollections.hasDuplicates(this.individualProviders)) {
                throw new AssertionError("Codegen error?  Duplicates in the provider list");
            } else if ($assertionsDisabled || !DaggerCollections.hasDuplicates(this.collectionProviders)) {
                return new SetFactory(this.individualProviders, this.collectionProviders);
            } else {
                throw new AssertionError("Codegen error?  Duplicates in the provider list");
            }
        }
    }

    public static <T> Builder<T> builder(int individualProviderSize, int collectionProviderSize) {
        return new Builder(individualProviderSize, collectionProviderSize);
    }

    private SetFactory(List<Provider<T>> individualProviders, List<Provider<Collection<T>>> collectionProviders) {
        this.individualProviders = individualProviders;
        this.collectionProviders = collectionProviders;
    }

    public Set<T> get() {
        int i;
        int size = this.individualProviders.size();
        List<Collection<T>> providedCollections = new ArrayList(this.collectionProviders.size());
        int c = this.collectionProviders.size();
        for (i = 0; i < c; i++) {
            Collection<T> providedCollection = (Collection) ((Provider) this.collectionProviders.get(i)).get();
            size += providedCollection.size();
            providedCollections.add(providedCollection);
        }
        Set<T> providedValues = DaggerCollections.newHashSetWithExpectedSize(size);
        c = this.individualProviders.size();
        for (i = 0; i < c; i++) {
            providedValues.add(Preconditions.checkNotNull(((Provider) this.individualProviders.get(i)).get()));
        }
        c = providedCollections.size();
        for (i = 0; i < c; i++) {
            for (T element : (Collection) providedCollections.get(i)) {
                providedValues.add(Preconditions.checkNotNull(element));
            }
        }
        return Collections.unmodifiableSet(providedValues);
    }
}
