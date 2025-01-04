package persistence.impl;

import domain.Category;
import persistence.CategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistence.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

    private static final String GET_CATEGORY_LIST =
            "SELECT NAME,PRICE ,IMGURL,DESRIPTION,ID AS categoryId FROM CATEGORY";
    private static final String GET_CATEGORY =
            "SELECT NAME,PRICE ,IMGURL,DESRIPTION,ID AS categoryId FROM CATEGORY WHERE NAME = ?";
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        try
        {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CATEGORY_LIST);
            while (resultSet.next())
            {
                Category category = new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("NAME"));
                category.setDesription(resultSet.getString("DESRIPTION"));
                category.setPrice(resultSet.getInt("PRICE"));
                category.setImgURL(resultSet.getString("IMGURL"));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                category = new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("NAME"));
                category.setDesription(resultSet.getString("DESRIPTION"));
                category.setPrice(resultSet.getInt("PRICE"));
                category.setImgURL(resultSet.getString("IMGURL"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return category;
    }
}
