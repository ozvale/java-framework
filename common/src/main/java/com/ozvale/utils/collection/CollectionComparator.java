package com.ozvale.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * 列表对比工具
 */
public class CollectionComparator<T> {

    private Collection<T> source;
    private Collection<T> target;

    private Collection<T> targetCreated = new ArrayList<>();
    private Collection<T> targetRemained = new ArrayList<>();
    private Collection<T> sourceDeleted = new ArrayList<>();
    private Collection<T> sourceRemained = new ArrayList<>();


    public static <T> CollectionComparator<T> create(Collection<T> source, Collection<T> target) {
        CollectionComparator<T> comparator = new CollectionComparator<>();
        comparator.setSource(source);
        comparator.setTarget(target);
        return comparator;
    }

    /**
     * 是否相等
     *
     * @return
     */
    public boolean isEqual() {
        return targetCreated.size() == 0 && sourceDeleted.size() == 0;
    }

    public CollectionComparator<T> execute() {
        return this.execute((source, target) -> source.equals(target));
    }

    public CollectionComparator<T> execute(EqualComparator<T> equalComparator) {
        Collection<T> sourceList = Optional.ofNullable(source).orElse(new ArrayList<>());
        Collection<T> targetList = Optional.ofNullable(target).orElse(new ArrayList<>());
        for (T t : targetList) {
            if (t == null) continue;
            if (!sourceList.stream().anyMatch(e -> equalComparator.equal(e, t))) {
                targetCreated.add(t);
            } else {
                targetRemained.add(t);
            }
        }
        for (T s : sourceList) {
            if (s == null) continue;
            if (!targetList.stream().anyMatch(e -> equalComparator.equal(s, e))) {
                sourceDeleted.add(s);
            } else {
                sourceRemained.add(s);
            }
        }
        return this;
    }

    public Collection<T> getSource() {
        return source;
    }

    public void setSource(Collection<T> source) {
        this.source = source;
    }

    public Collection<T> getTarget() {
        return target;
    }

    public void setTarget(Collection<T> target) {
        this.target = target;
    }

    public Collection<T> getTargetCreated() {
        return targetCreated;
    }

    public Collection<T> getSourceDeleted() {
        return sourceDeleted;
    }

    public Collection<T> getSourceRemained() {
        return sourceRemained;
    }

    public Collection<T> getTargetRemained() {
        return targetRemained;
    }

    public boolean isCreated(T t, EqualComparator<T> equalComparator) {
        return !Optional.ofNullable(source).map(s -> s.stream().anyMatch(a -> equalComparator.equal(a, t))).orElse(false);
    }

    public boolean isDeleted(T t, EqualComparator<T> equalComparator) {
        return !Optional.ofNullable(target).map(s -> s.stream().anyMatch(a -> equalComparator.equal(a, t))).orElse(false);
    }

    public interface EqualComparator<T> {
        boolean equal(T source, T target);
    }
}
