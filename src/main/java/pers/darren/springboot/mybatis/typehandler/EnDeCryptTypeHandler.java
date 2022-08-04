package pers.darren.springboot.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.darren.springboot.mybatis.EnDeCrypt;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static cn.hutool.crypto.SecureUtil.aes;
import static cn.hutool.crypto.SecureUtil.disableBouncyCastle;
import static java.nio.charset.StandardCharsets.UTF_8;

@MappedTypes(EnDeCrypt.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class EnDeCryptTypeHandler extends BaseTypeHandler<EnDeCrypt> {

    private static final Logger logger = LoggerFactory.getLogger(EnDeCryptTypeHandler.class);
    /**
     * 密钥
     */
    private static final byte[] SECRET_KEY = "darrenluo1993key".getBytes(UTF_8);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnDeCrypt parameter, JdbcType jdbcType) throws SQLException {
        logger.info("JdbcType of parameter>>>{}", jdbcType.name());
        if (parameter == null || parameter.getValue() == null) {
            ps.setString(i, null);
            return;
        }
        // 加密参数内容
        disableBouncyCastle();
        String encrypted = aes(SECRET_KEY).encryptHex(parameter.getValue());
        logger.info("Encrypted parameter>>>{}", encrypted);
        ps.setString(i, encrypted);
    }

    @Override
    public EnDeCrypt getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.decrypt(rs.getString(columnName));
    }

    @Override
    public EnDeCrypt getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.decrypt(rs.getString(columnIndex));
    }

    @Override
    public EnDeCrypt getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.decrypt(cs.getString(columnIndex));
    }

    private EnDeCrypt decrypt(String value) {
        if (value == null) {
            return null;
        }
        // 解密数据内容
        disableBouncyCastle();
        return new EnDeCrypt(aes(SECRET_KEY).decryptStr(value));
    }
}
