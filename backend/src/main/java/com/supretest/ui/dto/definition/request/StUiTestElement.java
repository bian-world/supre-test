package com.supretest.ui.dto.definition.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.metersphere.plugin.core.MsParameter;
import io.metersphere.plugin.core.utils.LogUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.jmeter.save.SaveService;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "clazzName"
)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public abstract class StUiTestElement {
    private String type;
    private String clazzName = "com.supretest.ui.dto.definition.request.StUiTestElement";
    @JSONField(
            ordinal = 1
    )
    private String id;
    @JSONField(
            ordinal = 2
    )
    private String name;
    @JSONField(
            ordinal = 3
    )
    private String label;
    @JSONField(
            ordinal = 4
    )
    private String resourceId;
    @JSONField(
            ordinal = 5
    )
    private String referenced;
    @JSONField(
            ordinal = 6
    )
    private boolean active;
    @JSONField(
            ordinal = 7
    )
    private String index;
    @JSONField(
            ordinal = 8
    )
    private boolean enable = true;
    @JSONField(
            ordinal = 9
    )
    private String refType;
    @JSONField(
            ordinal = 10
    )
    private LinkedList<com.supretest.ui.dto.definition.request.StUiTestElement> hashTree;
    @JSONField(
            ordinal = 12
    )
    private String projectId;
    @JSONField(
            ordinal = 13
    )
    private boolean isMockEnvironment;
    @JSONField(
            ordinal = 14
    )
    private String environmentId;
    @JSONField(
            ordinal = 15
    )
    private String pluginId;
    @JSONField(
            ordinal = 16
    )
    private String stepName;

    private com.supretest.ui.dto.definition.request.StUiTestElement parent;

    public void toHashTree(HashTree tree, List<com.supretest.ui.dto.definition.request.StUiTestElement> hashTree, MsParameter config) {
        if (CollectionUtils.isNotEmpty(hashTree)) {
            Iterator var4 = hashTree.iterator();

            while(var4.hasNext()) {
                com.supretest.ui.dto.definition.request.StUiTestElement el = (com.supretest.ui.dto.definition.request.StUiTestElement)var4.next();
                el.toHashTree(tree, el.hashTree, config);
            }
        }

    }

    public String getJmx(HashTree hashTree) {
        try {
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            Throwable var3 = null;

            String var4;
            try {
                SaveService.saveTree(hashTree, bas);
                var4 = bas.toString();
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (bas != null) {
                    if (var3 != null) {
                        try {
                            bas.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        bas.close();
                    }
                }

            }

            return var4;
        } catch (Exception var16) {
            var16.printStackTrace();
            LogUtil.warn("HashTree error, can't log jmx scenarioDefinition");
            return null;
        }
    }

    public HashTree generateHashTree(MsParameter config) {
        HashTree jmeterTestPlanHashTree = new ListedHashTree();
        this.toHashTree(jmeterTestPlanHashTree, this.hashTree, config);
        return jmeterTestPlanHashTree;
    }

    public StUiTestElement() {
    }

    public String getType() {
        return this.type;
    }

    public String getClazzName() {
        return this.clazzName;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLabel() {
        return this.label;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public String getReferenced() {
        return this.referenced;
    }

    public boolean isActive() {
        return this.active;
    }

    public String getIndex() {
        return this.index;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public String getRefType() {
        return this.refType;
    }

    public LinkedList<com.supretest.ui.dto.definition.request.StUiTestElement> getHashTree() {
        return this.hashTree;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public boolean isMockEnvironment() {
        return this.isMockEnvironment;
    }

    public String getEnvironmentId() {
        return this.environmentId;
    }

    public String getPluginId() {
        return this.pluginId;
    }

    public String getStepName() {
        return this.stepName;
    }

    public com.supretest.ui.dto.definition.request.StUiTestElement getParent() {
        return this.parent;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setReferenced(String referenced) {
        this.referenced = referenced;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public void setHashTree(LinkedList<com.supretest.ui.dto.definition.request.StUiTestElement> hashTree) {
        this.hashTree = hashTree;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setMockEnvironment(boolean isMockEnvironment) {
        this.isMockEnvironment = isMockEnvironment;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setParent(com.supretest.ui.dto.definition.request.StUiTestElement parent) {
        this.parent = parent;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof com.supretest.ui.dto.definition.request.StUiTestElement)) {
            return false;
        } else {
            com.supretest.ui.dto.definition.request.StUiTestElement other = (com.supretest.ui.dto.definition.request.StUiTestElement)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isActive() != other.isActive()) {
                return false;
            } else if (this.isEnable() != other.isEnable()) {
                return false;
            } else if (this.isMockEnvironment() != other.isMockEnvironment()) {
                return false;
            } else {
                label198: {
                    Object this$type = this.getType();
                    Object other$type = other.getType();
                    if (this$type == null) {
                        if (other$type == null) {
                            break label198;
                        }
                    } else if (this$type.equals(other$type)) {
                        break label198;
                    }

                    return false;
                }

                Object this$clazzName = this.getClazzName();
                Object other$clazzName = other.getClazzName();
                if (this$clazzName == null) {
                    if (other$clazzName != null) {
                        return false;
                    }
                } else if (!this$clazzName.equals(other$clazzName)) {
                    return false;
                }

                label184: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label184;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label184;
                    }

                    return false;
                }

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                label170: {
                    Object this$label = this.getLabel();
                    Object other$label = other.getLabel();
                    if (this$label == null) {
                        if (other$label == null) {
                            break label170;
                        }
                    } else if (this$label.equals(other$label)) {
                        break label170;
                    }

                    return false;
                }

                Object this$resourceId = this.getResourceId();
                Object other$resourceId = other.getResourceId();
                if (this$resourceId == null) {
                    if (other$resourceId != null) {
                        return false;
                    }
                } else if (!this$resourceId.equals(other$resourceId)) {
                    return false;
                }

                label156: {
                    Object this$referenced = this.getReferenced();
                    Object other$referenced = other.getReferenced();
                    if (this$referenced == null) {
                        if (other$referenced == null) {
                            break label156;
                        }
                    } else if (this$referenced.equals(other$referenced)) {
                        break label156;
                    }

                    return false;
                }

                Object this$index = this.getIndex();
                Object other$index = other.getIndex();
                if (this$index == null) {
                    if (other$index != null) {
                        return false;
                    }
                } else if (!this$index.equals(other$index)) {
                    return false;
                }

                label142: {
                    Object this$refType = this.getRefType();
                    Object other$refType = other.getRefType();
                    if (this$refType == null) {
                        if (other$refType == null) {
                            break label142;
                        }
                    } else if (this$refType.equals(other$refType)) {
                        break label142;
                    }

                    return false;
                }

                label135: {
                    Object this$hashTree = this.getHashTree();
                    Object other$hashTree = other.getHashTree();
                    if (this$hashTree == null) {
                        if (other$hashTree == null) {
                            break label135;
                        }
                    } else if (this$hashTree.equals(other$hashTree)) {
                        break label135;
                    }

                    return false;
                }

                Object this$projectId = this.getProjectId();
                Object other$projectId = other.getProjectId();
                if (this$projectId == null) {
                    if (other$projectId != null) {
                        return false;
                    }
                } else if (!this$projectId.equals(other$projectId)) {
                    return false;
                }

                label121: {
                    Object this$environmentId = this.getEnvironmentId();
                    Object other$environmentId = other.getEnvironmentId();
                    if (this$environmentId == null) {
                        if (other$environmentId == null) {
                            break label121;
                        }
                    } else if (this$environmentId.equals(other$environmentId)) {
                        break label121;
                    }

                    return false;
                }

                label114: {
                    Object this$pluginId = this.getPluginId();
                    Object other$pluginId = other.getPluginId();
                    if (this$pluginId == null) {
                        if (other$pluginId == null) {
                            break label114;
                        }
                    } else if (this$pluginId.equals(other$pluginId)) {
                        break label114;
                    }

                    return false;
                }

                Object this$stepName = this.getStepName();
                Object other$stepName = other.getStepName();
                if (this$stepName == null) {
                    if (other$stepName != null) {
                        return false;
                    }
                } else if (!this$stepName.equals(other$stepName)) {
                    return false;
                }

                Object this$parent = this.getParent();
                Object other$parent = other.getParent();
                if (this$parent == null) {
                    if (other$parent != null) {
                        return false;
                    }
                } else if (!this$parent.equals(other$parent)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof com.supretest.ui.dto.definition.request.StUiTestElement;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isActive() ? 79 : 97);
        result = result * 59 + (this.isEnable() ? 79 : 97);
        result = result * 59 + (this.isMockEnvironment() ? 79 : 97);
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $clazzName = this.getClazzName();
        result = result * 59 + ($clazzName == null ? 43 : $clazzName.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $label = this.getLabel();
        result = result * 59 + ($label == null ? 43 : $label.hashCode());
        Object $resourceId = this.getResourceId();
        result = result * 59 + ($resourceId == null ? 43 : $resourceId.hashCode());
        Object $referenced = this.getReferenced();
        result = result * 59 + ($referenced == null ? 43 : $referenced.hashCode());
        Object $index = this.getIndex();
        result = result * 59 + ($index == null ? 43 : $index.hashCode());
        Object $refType = this.getRefType();
        result = result * 59 + ($refType == null ? 43 : $refType.hashCode());
        Object $hashTree = this.getHashTree();
        result = result * 59 + ($hashTree == null ? 43 : $hashTree.hashCode());
        Object $projectId = this.getProjectId();
        result = result * 59 + ($projectId == null ? 43 : $projectId.hashCode());
        Object $environmentId = this.getEnvironmentId();
        result = result * 59 + ($environmentId == null ? 43 : $environmentId.hashCode());
        Object $pluginId = this.getPluginId();
        result = result * 59 + ($pluginId == null ? 43 : $pluginId.hashCode());
        Object $stepName = this.getStepName();
        result = result * 59 + ($stepName == null ? 43 : $stepName.hashCode());
        Object $parent = this.getParent();
        result = result * 59 + ($parent == null ? 43 : $parent.hashCode());
        return result;
    }

    public String toString() {
        return "MsTestElement(type=" + this.getType() + ", clazzName=" + this.getClazzName() + ", id=" + this.getId() + ", name=" + this.getName() + ", label=" + this.getLabel() + ", resourceId=" + this.getResourceId() + ", referenced=" + this.getReferenced() + ", active=" + this.isActive() + ", index=" + this.getIndex() + ", enable=" + this.isEnable() + ", refType=" + this.getRefType() + ", hashTree=" + this.getHashTree() + ", projectId=" + this.getProjectId() + ", isMockEnvironment=" + this.isMockEnvironment() + ", environmentId=" + this.getEnvironmentId() + ", pluginId=" + this.getPluginId() + ", stepName=" + this.getStepName() + ", parent=" + this.getParent() + ")";
    }
}
