package org.gradle.accessors.dm;

import java.util.Map;
import javax.inject.Inject;
import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.plugin.use.PluginDependency;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

  private final AbstractExternalDependencyFactory owner = this;
  private final IoLibraryAccessors laccForIoLibraryAccessors =
    new IoLibraryAccessors(owner);
  private final JunitLibraryAccessors laccForJunitLibraryAccessors =
    new JunitLibraryAccessors(owner);
  private final OrgLibraryAccessors laccForOrgLibraryAccessors =
    new OrgLibraryAccessors(owner);
  private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(
    providers,
    config
  );
  private final BundleAccessors baccForBundleAccessors = new BundleAccessors(
    objects,
    providers,
    config,
    attributesFactory,
    capabilityNotationParser
  );
  private final PluginAccessors paccForPluginAccessors = new PluginAccessors(
    providers,
    config
  );

  @Inject
  public LibrariesForLibs(
    DefaultVersionCatalog config,
    ProviderFactory providers,
    ObjectFactory objects,
    ImmutableAttributesFactory attributesFactory,
    CapabilityNotationParser capabilityNotationParser
  ) {
    super(
      config,
      providers,
      objects,
      attributesFactory,
      capabilityNotationParser
    );
  }

  /**
   * Returns the group of libraries at io
   */
  public IoLibraryAccessors getIo() {
    return laccForIoLibraryAccessors;
  }

  /**
   * Returns the group of libraries at junit
   */
  public JunitLibraryAccessors getJunit() {
    return laccForJunitLibraryAccessors;
  }

  /**
   * Returns the group of libraries at org
   */
  public OrgLibraryAccessors getOrg() {
    return laccForOrgLibraryAccessors;
  }

  /**
   * Returns the group of versions at versions
   */
  public VersionAccessors getVersions() {
    return vaccForVersionAccessors;
  }

  /**
   * Returns the group of bundles at bundles
   */
  public BundleAccessors getBundles() {
    return baccForBundleAccessors;
  }

  /**
   * Returns the group of plugins at plugins
   */
  public PluginAccessors getPlugins() {
    return paccForPluginAccessors;
  }

  public static class IoLibraryAccessors extends SubDependencyFactory {

    private final IoJavalinLibraryAccessors laccForIoJavalinLibraryAccessors =
      new IoJavalinLibraryAccessors(owner);
    private final IoJstachLibraryAccessors laccForIoJstachLibraryAccessors =
      new IoJstachLibraryAccessors(owner);

    public IoLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Returns the group of libraries at io.javalin
     */
    public IoJavalinLibraryAccessors getJavalin() {
      return laccForIoJavalinLibraryAccessors;
    }

    /**
     * Returns the group of libraries at io.jstach
     */
    public IoJstachLibraryAccessors getJstach() {
      return laccForIoJstachLibraryAccessors;
    }
  }

  public static class IoJavalinLibraryAccessors extends SubDependencyFactory {

    public IoJavalinLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Creates a dependency provider for javalin (io.javalin:javalin)
     * with versionRef 'io.javalin.javalin'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getJavalin() {
      return create("io.javalin.javalin");
    }
  }

  public static class IoJstachLibraryAccessors extends SubDependencyFactory {

    public IoJstachLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Creates a dependency provider for jstachio (io.jstach:jstachio)
     * with versionRef 'io.jstach.jstachio'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getJstachio() {
      return create("io.jstach.jstachio");
    }
  }

  public static class JunitLibraryAccessors extends SubDependencyFactory {

    public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Creates a dependency provider for junit (junit:junit)
     * with versionRef 'junit.junit'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getJunit() {
      return create("junit.junit");
    }
  }

  public static class OrgLibraryAccessors extends SubDependencyFactory {

    private final OrgApacheLibraryAccessors laccForOrgApacheLibraryAccessors =
      new OrgApacheLibraryAccessors(owner);
    private final OrgMockitoLibraryAccessors laccForOrgMockitoLibraryAccessors =
      new OrgMockitoLibraryAccessors(owner);
    private final OrgSlf4jLibraryAccessors laccForOrgSlf4jLibraryAccessors =
      new OrgSlf4jLibraryAccessors(owner);

    public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Returns the group of libraries at org.apache
     */
    public OrgApacheLibraryAccessors getApache() {
      return laccForOrgApacheLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org.mockito
     */
    public OrgMockitoLibraryAccessors getMockito() {
      return laccForOrgMockitoLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org.slf4j
     */
    public OrgSlf4jLibraryAccessors getSlf4j() {
      return laccForOrgSlf4jLibraryAccessors;
    }
  }

  public static class OrgApacheLibraryAccessors extends SubDependencyFactory {

    private final OrgApacheCommonsLibraryAccessors laccForOrgApacheCommonsLibraryAccessors =
      new OrgApacheCommonsLibraryAccessors(owner);

    public OrgApacheLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Returns the group of libraries at org.apache.commons
     */
    public OrgApacheCommonsLibraryAccessors getCommons() {
      return laccForOrgApacheCommonsLibraryAccessors;
    }
  }

  public static class OrgApacheCommonsLibraryAccessors
    extends SubDependencyFactory {

    private final OrgApacheCommonsCommonsLibraryAccessors laccForOrgApacheCommonsCommonsLibraryAccessors =
      new OrgApacheCommonsCommonsLibraryAccessors(owner);

    public OrgApacheCommonsLibraryAccessors(
      AbstractExternalDependencyFactory owner
    ) {
      super(owner);
    }

    /**
     * Returns the group of libraries at org.apache.commons.commons
     */
    public OrgApacheCommonsCommonsLibraryAccessors getCommons() {
      return laccForOrgApacheCommonsCommonsLibraryAccessors;
    }
  }

  public static class OrgApacheCommonsCommonsLibraryAccessors
    extends SubDependencyFactory {

    public OrgApacheCommonsCommonsLibraryAccessors(
      AbstractExternalDependencyFactory owner
    ) {
      super(owner);
    }

    /**
     * Creates a dependency provider for lang3 (org.apache.commons:commons-lang3)
     * with versionRef 'org.apache.commons.commons.lang3'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getLang3() {
      return create("org.apache.commons.commons.lang3");
    }

    /**
     * Creates a dependency provider for math3 (org.apache.commons:commons-math3)
     * with versionRef 'org.apache.commons.commons.math3'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getMath3() {
      return create("org.apache.commons.commons.math3");
    }
  }

  public static class OrgMockitoLibraryAccessors extends SubDependencyFactory {

    private final OrgMockitoMockitoLibraryAccessors laccForOrgMockitoMockitoLibraryAccessors =
      new OrgMockitoMockitoLibraryAccessors(owner);

    public OrgMockitoLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Returns the group of libraries at org.mockito.mockito
     */
    public OrgMockitoMockitoLibraryAccessors getMockito() {
      return laccForOrgMockitoMockitoLibraryAccessors;
    }
  }

  public static class OrgMockitoMockitoLibraryAccessors
    extends SubDependencyFactory {

    public OrgMockitoMockitoLibraryAccessors(
      AbstractExternalDependencyFactory owner
    ) {
      super(owner);
    }

    /**
     * Creates a dependency provider for core (org.mockito:mockito-core)
     * with versionRef 'org.mockito.mockito.core'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getCore() {
      return create("org.mockito.mockito.core");
    }
  }

  public static class OrgSlf4jLibraryAccessors extends SubDependencyFactory {

    private final OrgSlf4jSlf4jLibraryAccessors laccForOrgSlf4jSlf4jLibraryAccessors =
      new OrgSlf4jSlf4jLibraryAccessors(owner);

    public OrgSlf4jLibraryAccessors(AbstractExternalDependencyFactory owner) {
      super(owner);
    }

    /**
     * Returns the group of libraries at org.slf4j.slf4j
     */
    public OrgSlf4jSlf4jLibraryAccessors getSlf4j() {
      return laccForOrgSlf4jSlf4jLibraryAccessors;
    }
  }

  public static class OrgSlf4jSlf4jLibraryAccessors
    extends SubDependencyFactory {

    public OrgSlf4jSlf4jLibraryAccessors(
      AbstractExternalDependencyFactory owner
    ) {
      super(owner);
    }

    /**
     * Creates a dependency provider for simple (org.slf4j:slf4j-simple)
     * with versionRef 'org.slf4j.slf4j.simple'.
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getSimple() {
      return create("org.slf4j.slf4j.simple");
    }
  }

  public static class VersionAccessors extends VersionFactory {

    private final IoVersionAccessors vaccForIoVersionAccessors =
      new IoVersionAccessors(providers, config);
    private final JunitVersionAccessors vaccForJunitVersionAccessors =
      new JunitVersionAccessors(providers, config);
    private final OrgVersionAccessors vaccForOrgVersionAccessors =
      new OrgVersionAccessors(providers, config);

    public VersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.io
     */
    public IoVersionAccessors getIo() {
      return vaccForIoVersionAccessors;
    }

    /**
     * Returns the group of versions at versions.junit
     */
    public JunitVersionAccessors getJunit() {
      return vaccForJunitVersionAccessors;
    }

    /**
     * Returns the group of versions at versions.org
     */
    public OrgVersionAccessors getOrg() {
      return vaccForOrgVersionAccessors;
    }
  }

  public static class IoVersionAccessors extends VersionFactory {

    private final IoJavalinVersionAccessors vaccForIoJavalinVersionAccessors =
      new IoJavalinVersionAccessors(providers, config);
    private final IoJstachVersionAccessors vaccForIoJstachVersionAccessors =
      new IoJstachVersionAccessors(providers, config);

    public IoVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.io.javalin
     */
    public IoJavalinVersionAccessors getJavalin() {
      return vaccForIoJavalinVersionAccessors;
    }

    /**
     * Returns the group of versions at versions.io.jstach
     */
    public IoJstachVersionAccessors getJstach() {
      return vaccForIoJstachVersionAccessors;
    }
  }

  public static class IoJavalinVersionAccessors extends VersionFactory {

    public IoJavalinVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the version associated to this alias: io.javalin.javalin (6.1.2)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getJavalin() {
      return getVersion("io.javalin.javalin");
    }
  }

  public static class IoJstachVersionAccessors extends VersionFactory {

    public IoJstachVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the version associated to this alias: io.jstach.jstachio (1.3.4)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getJstachio() {
      return getVersion("io.jstach.jstachio");
    }
  }

  public static class JunitVersionAccessors extends VersionFactory {

    public JunitVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the version associated to this alias: junit.junit (4.13.2)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getJunit() {
      return getVersion("junit.junit");
    }
  }

  public static class OrgVersionAccessors extends VersionFactory {

    private final OrgApacheVersionAccessors vaccForOrgApacheVersionAccessors =
      new OrgApacheVersionAccessors(providers, config);
    private final OrgMockitoVersionAccessors vaccForOrgMockitoVersionAccessors =
      new OrgMockitoVersionAccessors(providers, config);
    private final OrgSlf4jVersionAccessors vaccForOrgSlf4jVersionAccessors =
      new OrgSlf4jVersionAccessors(providers, config);

    public OrgVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.org.apache
     */
    public OrgApacheVersionAccessors getApache() {
      return vaccForOrgApacheVersionAccessors;
    }

    /**
     * Returns the group of versions at versions.org.mockito
     */
    public OrgMockitoVersionAccessors getMockito() {
      return vaccForOrgMockitoVersionAccessors;
    }

    /**
     * Returns the group of versions at versions.org.slf4j
     */
    public OrgSlf4jVersionAccessors getSlf4j() {
      return vaccForOrgSlf4jVersionAccessors;
    }
  }

  public static class OrgApacheVersionAccessors extends VersionFactory {

    private final OrgApacheCommonsVersionAccessors vaccForOrgApacheCommonsVersionAccessors =
      new OrgApacheCommonsVersionAccessors(providers, config);

    public OrgApacheVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.org.apache.commons
     */
    public OrgApacheCommonsVersionAccessors getCommons() {
      return vaccForOrgApacheCommonsVersionAccessors;
    }
  }

  public static class OrgApacheCommonsVersionAccessors extends VersionFactory {

    private final OrgApacheCommonsCommonsVersionAccessors vaccForOrgApacheCommonsCommonsVersionAccessors =
      new OrgApacheCommonsCommonsVersionAccessors(providers, config);

    public OrgApacheCommonsVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.org.apache.commons.commons
     */
    public OrgApacheCommonsCommonsVersionAccessors getCommons() {
      return vaccForOrgApacheCommonsCommonsVersionAccessors;
    }
  }

  public static class OrgApacheCommonsCommonsVersionAccessors
    extends VersionFactory {

    public OrgApacheCommonsCommonsVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the version associated to this alias: org.apache.commons.commons.lang3 (3.14.0)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getLang3() {
      return getVersion("org.apache.commons.commons.lang3");
    }

    /**
     * Returns the version associated to this alias: org.apache.commons.commons.math3 (3.6.1)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getMath3() {
      return getVersion("org.apache.commons.commons.math3");
    }
  }

  public static class OrgMockitoVersionAccessors extends VersionFactory {

    private final OrgMockitoMockitoVersionAccessors vaccForOrgMockitoMockitoVersionAccessors =
      new OrgMockitoMockitoVersionAccessors(providers, config);

    public OrgMockitoVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.org.mockito.mockito
     */
    public OrgMockitoMockitoVersionAccessors getMockito() {
      return vaccForOrgMockitoMockitoVersionAccessors;
    }
  }

  public static class OrgMockitoMockitoVersionAccessors extends VersionFactory {

    public OrgMockitoMockitoVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the version associated to this alias: org.mockito.mockito.core (5.10.0)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getCore() {
      return getVersion("org.mockito.mockito.core");
    }
  }

  public static class OrgSlf4jVersionAccessors extends VersionFactory {

    private final OrgSlf4jSlf4jVersionAccessors vaccForOrgSlf4jSlf4jVersionAccessors =
      new OrgSlf4jSlf4jVersionAccessors(providers, config);

    public OrgSlf4jVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the group of versions at versions.org.slf4j.slf4j
     */
    public OrgSlf4jSlf4jVersionAccessors getSlf4j() {
      return vaccForOrgSlf4jSlf4jVersionAccessors;
    }
  }

  public static class OrgSlf4jSlf4jVersionAccessors extends VersionFactory {

    public OrgSlf4jSlf4jVersionAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }

    /**
     * Returns the version associated to this alias: org.slf4j.slf4j.simple (2.1.0-alpha1)
     * If the version is a rich version and that its not expressible as a
     * single version string, then an empty string is returned.
     * This version was declared in catalog libs.versions.toml
     */
    public Provider<String> getSimple() {
      return getVersion("org.slf4j.slf4j.simple");
    }
  }

  public static class BundleAccessors extends BundleFactory {

    public BundleAccessors(
      ObjectFactory objects,
      ProviderFactory providers,
      DefaultVersionCatalog config,
      ImmutableAttributesFactory attributesFactory,
      CapabilityNotationParser capabilityNotationParser
    ) {
      super(
        objects,
        providers,
        config,
        attributesFactory,
        capabilityNotationParser
      );
    }
  }

  public static class PluginAccessors extends PluginFactory {

    public PluginAccessors(
      ProviderFactory providers,
      DefaultVersionCatalog config
    ) {
      super(providers, config);
    }
  }
}
