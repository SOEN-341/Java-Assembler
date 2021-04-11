public interface IOptions {
    public boolean getHelp();
    public boolean getVerbose();
    public boolean getListing();
    public boolean getBanner();
    public void setListing(boolean listing);
    public void setHelp(boolean help);
    public void setVerbose(boolean verbose);
    public void setBanner(boolean banner);
}
