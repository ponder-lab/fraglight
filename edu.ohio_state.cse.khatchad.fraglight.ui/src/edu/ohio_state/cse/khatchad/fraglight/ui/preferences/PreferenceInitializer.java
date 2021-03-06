package edu.ohio_state.cse.khatchad.fraglight.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import edu.ohio_state.cse.khatchad.fraglight.core.analysis.PointcutAnalyzer;
import edu.ohio_state.cse.khatchad.fraglight.core.analysis.PointcutProcessor;
import edu.ohio_state.cse.khatchad.fraglight.ui.FraglightUiPlugin;
import edu.ohio_state.cse.khatchad.fraglight.ui.PointcutChangePredictionProvider;
import edu.ohio_state.cse.khatchad.fraglight.ui.PointcutChangePredictionProvider.PointcutAnalysisScope;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = FraglightUiPlugin.getDefault()
				.getPreferenceStore();
		PointcutChangePredictionProvider changePredictionProvider = FraglightUiPlugin
				.getDefault().getChangePredictionProvider();
		
		int maximumAnalysisDepth;
		double highChangeConfidenceThreshold;
		double lowChangeConfidenceThreshold;
		PointcutAnalysisScope scope;
		
		if ( changePredictionProvider == null ) {
			maximumAnalysisDepth = PointcutProcessor.DEFAULT_MAXIMUM_ANALYSIS_DEPTH;
			highChangeConfidenceThreshold = PointcutChangePredictionProvider.DEFAULT_HIGH_CHANGE_CONFIDENCE_THRESHOLD;
			lowChangeConfidenceThreshold = PointcutChangePredictionProvider.DEFAULT_LOW_CHANGE_CONFIDENCE_THRESHOLD;
			scope = PointcutChangePredictionProvider.DEFAULT_POINTCUT_ANALYSIS_SCOPE;
		}
		
		else {
			highChangeConfidenceThreshold = changePredictionProvider.getHighChangeConfidenceThreshold();
			lowChangeConfidenceThreshold = changePredictionProvider.getLowChangeConfidenceThreshold();
			
			PointcutAnalyzer analyzer = changePredictionProvider.getPointcutAnalyzer();
			
			if ( analyzer == null ) {
				maximumAnalysisDepth = PointcutProcessor.DEFAULT_MAXIMUM_ANALYSIS_DEPTH;
			}
			
			else {
				maximumAnalysisDepth = analyzer.getMaximumAnalysisDepth();	
			}
			
			scope = changePredictionProvider.getPointcutAnalysisScope();
			
		}	
		
		store.setDefault(PreferenceConstants.P_ANALYSIS_DEPTH,
				maximumAnalysisDepth);
		store.setDefault(PreferenceConstants.P_HIGH_THRESHOLD,
				highChangeConfidenceThreshold);
		store.setDefault(PreferenceConstants.P_LOW_THRESHOLD,
				lowChangeConfidenceThreshold);
		store.setDefault(PreferenceConstants.P_POINTCUT_SCOPE, scope.toString());
	}
}