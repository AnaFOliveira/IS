
package artifact;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the artifact package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Scoresabovex_QNAME = new QName("http://projeto_2_web/", "scoresabovex");
    private final static QName _TitlesResponse_QNAME = new QName("http://projeto_2_web/", "titlesResponse");
    private final static QName _Titles_QNAME = new QName("http://projeto_2_web/", "titles");
    private final static QName _AlbumGetinfoResponse_QNAME = new QName("http://projeto_2_web/", "album_getinfoResponse");
    private final static QName _AlbumBygenreResponse_QNAME = new QName("http://projeto_2_web/", "albumBygenreResponse");
    private final static QName _ArtistsResponse_QNAME = new QName("http://projeto_2_web/", "artistsResponse");
    private final static QName _TotalData_QNAME = new QName("http://projeto_2_web/", "total_data");
    private final static QName _TotalDataResponse_QNAME = new QName("http://projeto_2_web/", "total_dataResponse");
    private final static QName _Artists_QNAME = new QName("http://projeto_2_web/", "artists");
    private final static QName _AlbumBygenre_QNAME = new QName("http://projeto_2_web/", "albumBygenre");
    private final static QName _AlbumGetinfo_QNAME = new QName("http://projeto_2_web/", "album_getinfo");
    private final static QName _ScoresabovexResponse_QNAME = new QName("http://projeto_2_web/", "scoresabovexResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: artifact
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ScoresabovexResponse }
     * 
     */
    public ScoresabovexResponse createScoresabovexResponse() {
        return new ScoresabovexResponse();
    }

    /**
     * Create an instance of {@link AlbumBygenre }
     * 
     */
    public AlbumBygenre createAlbumBygenre() {
        return new AlbumBygenre();
    }

    /**
     * Create an instance of {@link AlbumGetinfo }
     * 
     */
    public AlbumGetinfo createAlbumGetinfo() {
        return new AlbumGetinfo();
    }

    /**
     * Create an instance of {@link Artists }
     * 
     */
    public Artists createArtists() {
        return new Artists();
    }

    /**
     * Create an instance of {@link TotalDataResponse }
     * 
     */
    public TotalDataResponse createTotalDataResponse() {
        return new TotalDataResponse();
    }

    /**
     * Create an instance of {@link ArtistsResponse }
     * 
     */
    public ArtistsResponse createArtistsResponse() {
        return new ArtistsResponse();
    }

    /**
     * Create an instance of {@link TotalData }
     * 
     */
    public TotalData createTotalData() {
        return new TotalData();
    }

    /**
     * Create an instance of {@link AlbumBygenreResponse }
     * 
     */
    public AlbumBygenreResponse createAlbumBygenreResponse() {
        return new AlbumBygenreResponse();
    }

    /**
     * Create an instance of {@link AlbumGetinfoResponse }
     * 
     */
    public AlbumGetinfoResponse createAlbumGetinfoResponse() {
        return new AlbumGetinfoResponse();
    }

    /**
     * Create an instance of {@link Titles }
     * 
     */
    public Titles createTitles() {
        return new Titles();
    }

    /**
     * Create an instance of {@link TitlesResponse }
     * 
     */
    public TitlesResponse createTitlesResponse() {
        return new TitlesResponse();
    }

    /**
     * Create an instance of {@link Scoresabovex }
     * 
     */
    public Scoresabovex createScoresabovex() {
        return new Scoresabovex();
    }

    /**
     * Create an instance of {@link Music }
     * 
     */
    public Music createMusic() {
        return new Music();
    }

    /**
     * Create an instance of {@link Vinyl }
     * 
     */
    public Vinyl createVinyl() {
        return new Vinyl();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Scoresabovex }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "scoresabovex")
    public JAXBElement<Scoresabovex> createScoresabovex(Scoresabovex value) {
        return new JAXBElement<Scoresabovex>(_Scoresabovex_QNAME, Scoresabovex.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TitlesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "titlesResponse")
    public JAXBElement<TitlesResponse> createTitlesResponse(TitlesResponse value) {
        return new JAXBElement<TitlesResponse>(_TitlesResponse_QNAME, TitlesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Titles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "titles")
    public JAXBElement<Titles> createTitles(Titles value) {
        return new JAXBElement<Titles>(_Titles_QNAME, Titles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlbumGetinfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "album_getinfoResponse")
    public JAXBElement<AlbumGetinfoResponse> createAlbumGetinfoResponse(AlbumGetinfoResponse value) {
        return new JAXBElement<AlbumGetinfoResponse>(_AlbumGetinfoResponse_QNAME, AlbumGetinfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlbumBygenreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "albumBygenreResponse")
    public JAXBElement<AlbumBygenreResponse> createAlbumBygenreResponse(AlbumBygenreResponse value) {
        return new JAXBElement<AlbumBygenreResponse>(_AlbumBygenreResponse_QNAME, AlbumBygenreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArtistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "artistsResponse")
    public JAXBElement<ArtistsResponse> createArtistsResponse(ArtistsResponse value) {
        return new JAXBElement<ArtistsResponse>(_ArtistsResponse_QNAME, ArtistsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TotalData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "total_data")
    public JAXBElement<TotalData> createTotalData(TotalData value) {
        return new JAXBElement<TotalData>(_TotalData_QNAME, TotalData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TotalDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "total_dataResponse")
    public JAXBElement<TotalDataResponse> createTotalDataResponse(TotalDataResponse value) {
        return new JAXBElement<TotalDataResponse>(_TotalDataResponse_QNAME, TotalDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Artists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "artists")
    public JAXBElement<Artists> createArtists(Artists value) {
        return new JAXBElement<Artists>(_Artists_QNAME, Artists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlbumBygenre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "albumBygenre")
    public JAXBElement<AlbumBygenre> createAlbumBygenre(AlbumBygenre value) {
        return new JAXBElement<AlbumBygenre>(_AlbumBygenre_QNAME, AlbumBygenre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlbumGetinfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "album_getinfo")
    public JAXBElement<AlbumGetinfo> createAlbumGetinfo(AlbumGetinfo value) {
        return new JAXBElement<AlbumGetinfo>(_AlbumGetinfo_QNAME, AlbumGetinfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScoresabovexResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://projeto_2_web/", name = "scoresabovexResponse")
    public JAXBElement<ScoresabovexResponse> createScoresabovexResponse(ScoresabovexResponse value) {
        return new JAXBElement<ScoresabovexResponse>(_ScoresabovexResponse_QNAME, ScoresabovexResponse.class, null, value);
    }

}
