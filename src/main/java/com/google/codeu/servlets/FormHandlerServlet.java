package com.google.codeu.servlets;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;

/**
 * When the user submits the form, Blobstore processes the file upload
 * and then forwards the request to this servlet. This servlet can then
 * process the request using the file URL we get from Blobstore.
 */
@WebServlet("/my-form-handler")
public class FormHandlerServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("application/json");

    String user = request.getParameter("user");

    if (user == null || user.equals("")) {
      // Request is invalid, return empty array
      response.getWriter().println("[]");
      return;
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserLoggedIn()) {
      response.sendRedirect("/index.html");
      return;
    }
	
    String user = userService.getCurrentUser().getEmail();
    String userMessage = Jsoup.clean(request.getParameter("text"), Whitelist.none());
    TextProcessor processor = BBProcessorFactory.getInstance().create();
    String input= processor.process( userMessage ) ;
    String city = Jsoup.clean(request.getParameter("autocomplete_search"), Whitelist.none());
    double alat = Double.parseDouble(request.getParameter("alat"));
    long timestamp = Long.parseLong(request.getParameter("time"));
    double along = Double.parseDouble(request.getParameter("along"));
    List <String> imageUrls = getUploadedFileUrl(request, "image");
		
	if (imageUrls != null) {
		input = input + "<br/>";
		for (String imageUrl: imageUrls) {
				imageUrl = "<a href=\"" + imageUrl + "\">" + "<img src=\"" + imageUrl + "\"/>" + "</a>";
				input = input + imageUrl;
		}
	}
      System.out.println(timestamp);
      Message message = null;
      message = new Message(user, input,city,alat,along,timestamp);
      datastore.storeMessage(message);
	  response.sendRedirect("/user-page.html?user=" + user);
  }

  /**
   * Returns a URL that points to the uploaded file, or null if the user didn't upload a file.
   */
  private List <String> getUploadedFileUrl(HttpServletRequest request, String formInputElementName){
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
    List<BlobKey> blobKeys = blobs.get("image");
    List<String> imageUrls = new ArrayList <String>();
    int v = 0;
	
	// User submitted form without selecting a file, so we can't get a URL. (devserver)
    if(blobKeys == null || blobKeys.isEmpty())
		return null;

	for (String k: blobs.keySet()) {
		System.out.println("Key : " + k + " Val : " + blobs.get(k));
		System.out.println("Length of  List<BlobKey> blobKeys: " + blobKeys.size());

		for (BlobKey blobKey: blobKeys)	{
			BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKey);
			if (blobInfo.getSize() == 0) {
			  blobstoreService.delete(blobKey);
			  v = 1;
			  break;
			}

			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
			imageUrls.add(imagesService.getServingUrl(options));
		}
	}
	
	if (v == 0) return imageUrls;
	return null;
  }
}
