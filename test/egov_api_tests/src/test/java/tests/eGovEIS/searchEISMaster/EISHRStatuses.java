package tests.eGovEIS.searchEISMaster;

import builders.eGovEIS.searchEISMaster.RequestInfoBuilder;
import builders.eGovEIS.searchEISMaster.SearchEmployeeMasterRequestBuilder;
import com.jayway.restassured.response.Response;
import entities.requests.eGovEIS.searchEISMaster.RequestInfo;
import entities.requests.eGovEIS.searchEISMaster.SearchEmployeeMasterRequest;
import entities.responses.eGovEIS.searchEISMasters.hrStatuses.SearchHRStatusesResponse;
import entities.responses.login.LoginResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import resources.searchEISMaster.EISMasterResource;
import utils.*;

import java.io.IOException;

import static data.UserData.NARASAPPA;

public class EISHRStatuses {

    @Test(groups = {Categories.HR, Categories.SANITY})
    public void searchHRStatusesTest() throws IOException {

        // Login Test
        LoginResponse loginResponse = LoginAndLogoutHelper.login(NARASAPPA);

        // Search hrStatuses Test
        searchHRStatusesTestMethod(loginResponse);
    }

    private void searchHRStatusesTestMethod(LoginResponse loginResponse) throws IOException {

        RequestInfo requestInfo = new RequestInfoBuilder()
                .withAuthToken(loginResponse.getAccess_token())
                .build();

        SearchEmployeeMasterRequest searchEmployeeMasterRequest = new SearchEmployeeMasterRequestBuilder()
                .withRequestInfo(requestInfo)
                .build();

        Response response = new EISMasterResource().
                searchHRStatusesType(RequestHelper.getJsonString(searchEmployeeMasterRequest));

        SearchHRStatusesResponse searchHRStatusesResponse = (SearchHRStatusesResponse)
                ResponseHelper.getResponseAsObject(response.asString(), SearchHRStatusesResponse.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(searchHRStatusesResponse.getHRStatus().length, 3);

        new APILogger().log("Search HR Statuses Test is Completed--");
    }

}