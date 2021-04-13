public class Options implements IOptions {
    private boolean listing = false;
    private boolean verbose = false;
    private boolean help = false;
    private boolean banner = false;

    public Options(String[] args){
        for(int i = 0; i < args.length; i++){
            switch (args[i]){
                case "-h":
                case "-help":
                    setHelp(true);
                    break;
                case "-v":
                case "-verbose":
                    setVerbose(true);
                    break;
                case "-b":
                case "-banner":
                    setBanner(true);
                    break;
                case "-l":
                case "-listing":
                    setListing(true);
                    break;
                default:

            }
        }



    }

    public boolean getListing() {
        return listing;
    }

    public boolean getVerbose() {
        return verbose;
    }

    public boolean getHelp() {
        return help;
    }

    public boolean getBanner() {
        return banner;
    }

    public void setListing(boolean listing) {
        this.listing = listing;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public void setBanner(boolean banner) {
        this.banner = banner;
    }

    @Override
    public String toString() {
        return "Options{" +
                "listing=" + listing +
                ", verbose=" + verbose +
                ", help=" + help +
                ", banner=" + banner +
                '}';
    }
}
