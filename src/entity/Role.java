package entity;

public sealed interface Role permits Player, Coach{
    public String getRoleName();
}
