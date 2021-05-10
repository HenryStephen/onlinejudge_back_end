package cn.edu.nciae.onlinejudge.judge.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName compile
 */
@TableName(value ="compile")
@Data
public class Compile implements Serializable {
    /**
     * 编译配置的id
     */
    @TableId(value = "compile_id", type = IdType.AUTO)
    private Integer compile_id;

    /**
     * 配置的名称
     */
    @TableField(value = "config_name")
    private String config_name;

    /**
     *
     */
    @TableField(value = "src_name")
    private String src_name;

    /**
     *
     */
    @TableField(value = "exe_name")
    private String exe_name;

    /**
     *
     */
    @TableField(value = "max_cpu_time")
    private Long max_cpu_time;

    /**
     *
     */
    @TableField(value = "max_real_time")
    private Long max_real_time;

    /**
     *
     */
    @TableField(value = "max_memory")
    private Long max_memory;

    /**
     * 编译命令
     */
    @TableField(value = "compile_command")
    private String compile_command;

    /**
     * 环境变量
     */
    @TableField(value = "env")
    private String env;

    @TableField(exist = false)
    private static final long serialVersionUID = -1878680911152850866L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Compile other = (Compile) that;
        return (this.getCompile_id() == null ? other.getCompile_id() == null : this.getCompile_id().equals(other.getCompile_id()))
            && (this.getConfig_name() == null ? other.getConfig_name() == null : this.getConfig_name().equals(other.getConfig_name()))
            && (this.getSrc_name() == null ? other.getSrc_name() == null : this.getSrc_name().equals(other.getSrc_name()))
            && (this.getExe_name() == null ? other.getExe_name() == null : this.getExe_name().equals(other.getExe_name()))
            && (this.getMax_cpu_time() == null ? other.getMax_cpu_time() == null : this.getMax_cpu_time().equals(other.getMax_cpu_time()))
            && (this.getMax_real_time() == null ? other.getMax_real_time() == null : this.getMax_real_time().equals(other.getMax_real_time()))
            && (this.getMax_memory() == null ? other.getMax_memory() == null : this.getMax_memory().equals(other.getMax_memory()))
            && (this.getCompile_command() == null ? other.getCompile_command() == null : this.getCompile_command().equals(other.getCompile_command()))
            && (this.getEnv() == null ? other.getEnv() == null : this.getEnv().equals(other.getEnv()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompile_id() == null) ? 0 : getCompile_id().hashCode());
        result = prime * result + ((getConfig_name() == null) ? 0 : getConfig_name().hashCode());
        result = prime * result + ((getSrc_name() == null) ? 0 : getSrc_name().hashCode());
        result = prime * result + ((getExe_name() == null) ? 0 : getExe_name().hashCode());
        result = prime * result + ((getMax_cpu_time() == null) ? 0 : getMax_cpu_time().hashCode());
        result = prime * result + ((getMax_real_time() == null) ? 0 : getMax_real_time().hashCode());
        result = prime * result + ((getMax_memory() == null) ? 0 : getMax_memory().hashCode());
        result = prime * result + ((getCompile_command() == null) ? 0 : getCompile_command().hashCode());
        result = prime * result + ((getEnv() == null) ? 0 : getEnv().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", compile_id=").append(compile_id);
        sb.append(", config_name=").append(config_name);
        sb.append(", src_name=").append(src_name);
        sb.append(", exe_name=").append(exe_name);
        sb.append(", max_cpu_time=").append(max_cpu_time);
        sb.append(", max_real_time=").append(max_real_time);
        sb.append(", max_memory=").append(max_memory);
        sb.append(", compile_command=").append(compile_command);
        sb.append(", env=").append(env);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
