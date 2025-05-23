package ru.javaboys.defidog.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JmixEntity
@Table(name = "SOURCE_CODE_SECURITY_SCAN_JOB", indexes = {
        @Index(name = "IDX_SOURCE_CODE_SECURITY_SCAN_JOB_SOURCE_CODE_CHANGE_SET", columnList = "SOURCE_CODE_CHANGE_SET_ID"),
        @Index(name = "IDX_SOURCE_CODE_SECURITY_SCAN_JOB_AUDIT_REPORT", columnList = "AUDIT_REPORT_ID")
})
@Entity
@Getter
@Setter
public class SourceCodeSecurityScanJob {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "STATUS")
    private String status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "sourceCodeSecurityScanJob")
    private ScanTool scanTool;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "SOURCE_CODE_CHANGE_SET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SourceCodeChangeSet sourceCodeChangeSet;

    @Comment("Необработанные результаты сканирования исходников")
    @Column(name = "RAW_OUTPUT")
    @Lob
    private String rawOutput;

    @Comment("Подробная информация о запуске docker-контейнера сканера")
    @Column(name = "EXECUTION_LOG")
    @Lob
    private String executionLog;

    @JoinColumn(name = "AUDIT_REPORT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private AuditReport auditReport;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private OffsetDateTime lastModifiedDate;

    public SecurityScanJobStatus getStatus() {
        return status != null ? SecurityScanJobStatus.valueOf(status) : null;
    }

    public void setStatus(SecurityScanJobStatus status) {
        this.status = status != null ? status.toString() : null;
    }
}