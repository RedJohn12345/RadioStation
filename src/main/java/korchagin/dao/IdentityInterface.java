package korchagin.dao;

public interface IdentityInterface<I> {
    I getIdentity();
    void setIdentity(I identity);
}
