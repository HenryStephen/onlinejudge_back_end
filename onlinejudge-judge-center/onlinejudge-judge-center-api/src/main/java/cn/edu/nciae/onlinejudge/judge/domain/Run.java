package cn.edu.nciae.onlinejudge.judge.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName run
 */
@TableName(value ="run")
@Data
public class Run implements Serializable {
    /**
     * 运行时配置的id
     */
    @TableId(value = "run_id", type = IdType.AUTO)
    private Integer run_id;

    /**
     * 配置的名称
     */
    @TableField(value = "config_name")
    private String config_name;

    /**
     *
     */
    @TableField(value = "command")
    private String command;

    /**
     *
     */
    @TableField(value = "seccomp_rule")
    private String seccomp_rule;

    /**
     *
     */
    @TableField(value = "exe_name")
    private String exe_name;

    /**
     *
     */
    @TableField(value = "env")
    private String env;

    /**
     *
     */
    @TableField(value = "memory_limit_check_only")
    private Integer memory_limit_check_only;

    @TableField(exist = false)
    private static final long serialVersionUID = -3537478646255030568L;

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
        Run other = (Run) that;
        return (this.getRun_id() == null ? other.getRun_id() == null : this.getRun_id().equals(other.getRun_id()))
            && (this.getConfig_name() == null ? other.getConfig_name() == null : this.getConfig_name().equals(other.getConfig_name()))
            && (this.getCommand() == null ? other.getCommand() == null : this.getCommand().equals(other.getCommand()))
            && (this.getSeccomp_rule() == null ? other.getSeccomp_rule() == null : this.getSeccomp_rule().equals(other.getSeccomp_rule()))
            && (this.getExe_name() == null ? other.getExe_name() == null : this.getExe_name().equals(other.getExe_name()))
            && (this.getEnv() == null ? other.getEnv() == null : this.getEnv().equals(other.getEnv()))
            && (this.getMemory_limit_check_only() == null ? other.getMemory_limit_check_only() == null : this.getMemory_limit_check_only().equals(other.getMemory_limit_check_only()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRun_id() == null) ? 0 : getRun_id().hashCode());
        result = prime * result + ((getConfig_name() == null) ? 0 : getConfig_name().hashCode());
        result = prime * result + ((getCommand() == null) ? 0 : getCommand().hashCode());
        result = prime * result + ((getSeccomp_rule() == null) ? 0 : getSeccomp_rule().hashCode());
        result = prime * result + ((getExe_name() == null) ? 0 : getExe_name().hashCode());
        result = prime * result + ((getEnv() == null) ? 0 : getEnv().hashCode());
        result = prime * result + ((getMemory_limit_check_only() == null) ? 0 : getMemory_limit_check_only().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", run_id=").append(run_id);
        sb.append(", config_name=").append(config_name);
        sb.append(", command=").append(command);
        sb.append(", seccomp_rule=").append(seccomp_rule);
        sb.append(", exe_name=").append(exe_name);
        sb.append(", env=").append(env);
        sb.append(", memory_limit_check_only=").append(memory_limit_check_only);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
