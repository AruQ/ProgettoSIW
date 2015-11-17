package project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.service.PointsCalculatorStub;
import ws.service.PointsCalculatorStub.GetMaxPoints;
import ws.service.PointsCalculatorStub.SumPoints;
import ws.service.PointsCalculatorStub.SumPointsResponse;

@WebServlet(name = "TotalPointsServlet", urlPatterns = { "/TotalPointsServlet" })
public class TotalPointsServlet extends HttpServlet
{
	private static final long serialVersionUID = 4677773143824100790L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String toReturn = "";

		String primi = req.getParameter("primi");
		String secondi = req.getParameter("secondi");
		String contorni = req.getParameter("contorni");
		System.out.println("primi " + primi + " secondi " + secondi + " contorni " + contorni);

		if (primi != null && secondi != null && contorni != null)
		{
			PointsCalculatorStub pointsCalculatorStub = new PointsCalculatorStub();
			SumPoints sumPoints = new SumPoints();

			sumPoints.setNumContorni(Integer.parseInt(contorni));
			sumPoints.setNumPrimi(Integer.parseInt(primi));
			sumPoints.setNumSecondi(Integer.parseInt(secondi));
			SumPointsResponse response = pointsCalculatorStub.sumPoints(sumPoints);
			int totalPoints = response.get_return();
			System.out.println(totalPoints);

			int maxPoints = pointsCalculatorStub.getMaxPoints(new GetMaxPoints()).get_return();
			if (maxPoints >= totalPoints)
				toReturn += "'valid': 'true',";
			else
				toReturn += "'valid': 'false',";
			toReturn += "'totalPoints':'" + Integer.toString(totalPoints) + "',";
			toReturn += "'maxPoints':'" + Integer.toString(maxPoints) + "'";
			resp.getWriter().write("{" + toReturn + "}");
		}

	}

}
