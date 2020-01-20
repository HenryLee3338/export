package cn.henry.dao.cargo;

import cn.henry.domain.cargo.Packing;
import cn.henry.domain.cargo.PackingExample;

import java.util.List;

public interface PackingDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String packingListId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    int insert(Packing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    int insertSelective(Packing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    List<Packing> selectByExample(PackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    Packing selectByPrimaryKey(String packingListId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Packing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table packing_list_c
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Packing record);
}