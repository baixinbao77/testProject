package jxd.bxb.test.Connect.Conn.type;

/**
 * @ClassName DbTableType
 * @Description TODO
 * @Author 白新报
 * @Date 2022/10/28 8:04
 * @Version 1.0
 **/
public enum DbTableType {
    /**
     * Scalar function 标量函数 
     */
    FN,
    /**
     *  Assembly (CLR) scalar-function 组装(CLR)标量函数 
     */
    FS,
    /**
     *  Assembly (CLR) table-valued function 程序集(CLR)表值函数
     */
    FT,
    /**
     * In-lined table-function 内嵌表函数
     */
    IF,
    /**
     * Internal table 内部表
     */
    IT,
    /**
     *  Stored procedure 存储过程
     */
    P,
    /**
     *  Assembly (CLR) stored-procedure 组装(CLR)存储过程
     */
    PC,
    /**
     * PRIMARY KEY constraint (type is K) 主键约束（类型是K）
     */
    PK,
    /**
     * Replication filter stored procedure 制筛选存储过程
     */
    RF,
    /**
     *  System table 系统表
     */
    S,
    /**
     *  Synonym 同义词
     */
    SN,
    /**
     * Service queue 服务序列
     */
    SQ,
    /**
     *  Assembly (CLR) DML trigger 装配(CLR) DML触发器
     */
    TA,
    /**
     *  Table function 表函数
     */
    TF,
    /**
     *  SQL DML Trigger 触发器
     */
    TR,
    /**
     *  Table type
     */
    TT,
    /**
     *  User table用户表
     */
    U,
    /**
     *  UNIQUE constraint (type is K) 唯一约束（类型是K）
     */
    UQ,
    /**
     *  View 视图
     */
    V,
    /**
     *  Extended stored procedure 扩展存储过程
     */
    X,
    /**
     * Aggregate function (CLR) 聚合函数(CLR) 
     */
    AF,
    /**
     * CHECK constraint CHECK约束 
     */
    C,
    /**
     * Default or DEFAULT constraint 默认或DEFAULT约束
     */
    D,
    /**
     * FOREIGN KEY constraint 外键约束
     */
    F,
    /**
     * Log 日志
     */
    L,



}
